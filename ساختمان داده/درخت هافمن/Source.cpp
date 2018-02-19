#include <iostream>
#include <fstream>
#include <string>
#include <string.h>
#include <stack>

#define Size 500

using namespace std;

class node{
public:
	node *left;
	int info;
	char ch;
	int code;
	bool visited;
	node *right;
};

class HuffmanTree{
private:
	node *root;
public:
	HuffmanTree();
	node* getRoot();
	int makeTree( node*, int );
};

HuffmanTree::HuffmanTree(){
	root = NULL;
}

node* HuffmanTree::getRoot(){
	return root;
}

void Sort( node *a ){
	int sw = 0;
	node t;
	while( !sw ){
		sw = 1;
		for( int i = 0; i < Size - 1; i ++ ){
			if( a[ i ].info > a[ i + 1 ].info ){
				t.ch = a[ i ].ch;
				t.info = a[ i ].info;
				t.left = a[ i ].left;
				t.right = a[ i ].right;
				a[ i ].ch = a[ i + 1 ].ch;
				a[ i ].info = a[ i + 1 ].info;
				a[ i ].left = a[ i + 1 ].left;
				a[ i ].right = a[ i + 1 ].right;
				a[ i + 1 ].ch = t.ch;
				a[ i + 1 ].info = t.info;
				a[ i + 1 ].left = t.left;
				a[ i + 1 ].right = t.right;
				sw = 0;
			} else if( a[ i ].info == a[ i + 1 ].info && a[ i ].ch && a[ i + 1 ].ch && a[ i ].ch > a[ i + 1 ].ch ){
				t.ch = a[ i ].ch;
				t.info = a[ i ].info;
				t.left = a[ i ].left;
				t.right = a[ i ].right;
				a[ i ].ch = a[ i + 1 ].ch;
				a[ i ].info = a[ i + 1 ].info;
				a[ i ].left = a[ i + 1 ].left;
				a[ i ].right = a[ i + 1 ].right;
				a[ i + 1 ].ch = t.ch;
				a[ i + 1 ].info = t.info;
				a[ i + 1 ].left = t.left;
				a[ i + 1 ].right = t.right;
				sw = 0;
			}
		}
	}
}

void Shift( node *a ){
	int pos = 0;
	for(; a[ pos ].info == 0 ; pos ++ );
	for( int i = pos; i < Size; i ++ ){
		if( i + 2 < Size )
			a[ i ] = a[ i + 2 ];
	}
	a[ Size - 2 ].info = 0;
	a[ Size - 1 ].info = 0;
	//Sort(a);
}


int HuffmanTree::makeTree( node *a, int pos ){
	node *p, *q, *t;
	node *g;
	int used = 1;
	p = new node;
	p->info = a[ pos ].info;
	p->visited = false;
	p->ch = a[ pos ].ch;
	p->left = a[ pos ].left;
	p->right = a[ pos ].right;
	q = new node;
	if( a[ pos + 1 ].info ){
		q->info = a[ pos + 1 ].info;
		q->ch = a[ pos + 1 ].ch;
		q->visited = false;
		q->left = a[ pos + 1 ].left;
		q->right = a[ pos + 1 ].right;
	}
	if( pos + 1 == Size ){
		return 0;
	}
	//-----------
	t = new node;
	t->ch = NULL;
	t->visited = false;
	t->info = p->info + q->info;
	t->left = p;
	p->code = 0;
	t->right = q;
	q->code = 1;
	t->code = -1;
	root = t;
	Shift(a);
	a[ Size - 1 ] = *t;
	return used; 
}

void show( node *a ){
	int pos = 0;
	for(; a[ pos ].info == 0 ; pos ++ );
	for( ; pos < Size; pos ++ ){
		cout << a[ pos ].ch << " : " << a[ pos ].info << endl;
	}
	cout << "------------------------------------\n";
}

HuffmanTree ht;

void solve( node *a ){
	//show(a);
	Sort(a);
	//show(a);
	int pos = 0;
	for(; a[ pos ].info == 0 ; pos ++ );
	int h = 1;
	while( h ){
		h = ht.makeTree( a, pos );
		Sort(a);
		//show(a);
		for(; a[ pos ].info == 0 ; pos ++ );
	}
	string ss = "";
	string sss = "codes.txt" ;
	fstream out(sss);
	if( !out ){
		ofstream outt(sss);
		outt.close();
	}
	//DFS :-----------------------------------
	stack<node*> s;
	int sw;
	s.push(ht.getRoot());
	while( !s.empty() ){
		sw = 0;
		if( s.top()->left && s.top()->left->visited == false ){
			ss += s.top()->left->code + 48;
			s.top()->left->visited = true;
			s.push(s.top()->left);
			sw = 1;
		}else if( s.top()->right && s.top()->right->visited == false ){
			ss += s.top()->right->code + 48;
			s.top()->right->visited = true;
			s.push(s.top()->right);
			sw = 1;
		}
		if( sw && s.top()->ch > 31 ){
			cout << s.top()->ch << " : " << ss << endl;
			out << s.top()->ch << " : " << ss << endl;
		}
		if( !sw && ss.size() > 0 ){
			ss.erase(ss.size() - 1);
			s.pop();
		}
		if( ss.size() == 0 && s.top()->right->visited && s.top() == ht.getRoot() )
			break;
	}
	//----------------------------------------
}

int main(){
	//input data[ Size ];
	node data[ Size ];
	for( int i = 0; i < Size; i ++ ){
		data[ i ].ch = i + 10;
		data[ i ].left = data[ i ].right = NULL;
		data[ i ].visited = false;
		data[ i ].info = 0;
	}
	string s = "file1.txt" ;
	fstream in(s);
	if( !in ){
		cout<<"File doesn't exists!\n";
		return 1;
	}
	char ch;
	while( in >> ch ){
		data[ ch - 10 ].info ++;
	}
	solve(data);
	return 0;
}