var request = require('request'),
  q = require('q'),
  cheerio = require('cheerio'),
  path = require('path'),
  fs = require('fs');

var urls = [
  'https://www.amazon.com/'
];

var counter = 0;
var targetNumber = 3;

/**
 * @param link
 * @returns {*}
 * @description check if a url is already added to array or not
 */
function repeatitive (link) {
  for (var i = 0; i < urls.length; i++) {
    if (urls[ i ] == link) {
      return true;
    }
  }
  return false;
}

/**
 * @param url
 * @returns {*}
 * @description send a request to an url and return body
 */
function sendRequest (url) {
  var deferred = q.defer();

  request(url,
    {
      timeout: 60000
    },
    function (error, response, body) {
      if (!error && response.statusCode === 200) {
        body = body.toString().replace(/<!--[\s\S]*?(-->|--!>)/g, '');  // delete commented tag
        deferred.resolve(body);
      }
      else {
        console.log('failed request with address ', url);
        deferred.reject(error);
      }
    });

  return deferred.promise;
}

/**
 * @param url
 * @param body
 * @returns {*}
 * @description parse the body and finds `a` tags
 */
function bodyParser (url, body) {
  var deferred = q.defer(),
    $ = cheerio.load(body);

  try {
    $('a').each(function (index, value) {
      if (value.attribs.href && !repeatitive(value.attribs.href)) {
        if(!value.attribs.href.startsWith("http")) {
          value.attribs.href = url + '/' + value.attribs.href;
        }
        urls.push(value.attribs.href);
        deferred.resolve();
      }
    });
  }
  catch (e) {
    deferred.reject(e);
  }

  return deferred.promise;
}

/**
 * @param name
 * @param object
 * @returns {*}
 * @description write an object into file
 */
function fileWriter (name, object) {
  var deferred = q.defer();
  var p = path.join(__dirname, '/data/');
  try {
    fs.writeFile(p + name + '.' + 'txt', object, function (error) {
      if (error) {
        console.log('> ', error);
        deferred.reject(error);
      }
      else {
        deferred.resolve();
      }
    });
  }
  catch (error) {
    deferred.reject({ error: 'Error' });
  }

  return deferred.promise;
}

/**
 * @param url
 * @returns {*}
 * @description extract hostname of a url passed
 */
function extractHostname(url) {
  var hostname;
  //find & remove protocol (http, ftp, etc.) and get hostname

  if (url.indexOf("://") > -1) {
    hostname = url.split('/')[2];
  }
  else {
    hostname = url.split('/')[0];
  }

  //find & remove port number
  hostname = hostname.split(':')[0];
  //find & remove "?"
  hostname = hostname.split('?')[0];

  return hostname;
}

/**
 * @param x
 * @returns {*}
 * @description send request to index x of array, extract links and write the body into a file
 */
function f (x) {
  sendRequest(urls[ x ])
    .then(function (result) {
      bodyParser(urls[ x ], result)
        .then(function (res) {
          fileWriter(extractHostname(urls[x]) + '-' + x, result)
            .then(function (res) {
              if (counter > targetNumber) {
                console.log('Done', 'pages saved');
                console.log('--->', urls);
              }
              else {
                f(++ counter);
              }
            })
            .catch(function (err) {
              console.log('>>', err);
              if (counter > targetNumber) {
                console.log('Done', 'pages saved');
                console.log('--->', urls);
              }
              else {
                f(++ counter);
              }
            });
        })
        .catch(function (error) {
          console.log('>> ', error);
        });
    })
    .catch(function (error) {
      f(++ counter);
    });
}

function main (length) {
  
  targetNumber = length;
  f(counter);

}

exports.main = main;