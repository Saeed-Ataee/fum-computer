#include <iostream>

using namespace std;

class sentence{
public:
	int coef;
	int expo;
	sentence *next;
};

class poly{
public:
	poly();
	void add( int ,int );
	sentence *head;
	void print();
};

poly::poly(){
	head = new sentence;
	head->coef = 0;
	head->expo = 0;
	head->next = NULL;
};

void poly::add(int c, int e){
	int cc = 1;
	sentence *a, *q;
	a = new sentence;
	a->coef = c;
	a->expo = e;
	a->next = NULL;
	q = head;
	if( !head ){
		head->expo = 1;
		head->next = a;
	}else{
		while( q->next ){
			cc ++;
			q = q->next;
		}
		q->next = a;
		head->expo = cc;
	}
}

void poly::print(){
	sentence *cc = head->next;
	while( cc ){
		cout << cc->coef <<" x ^ " <<cc->expo;
		if( cc->next )
			cout << " + ";
		cc = cc->next;
	}
	cout << endl;
	cout << endl;
}

void multiply( poly *h1, poly *h2, poly h3 ){
	sentence *p, *q, *last;
	p = h1->head->next;
	last = h3.head;
	int a, b;
	int sw;
	while( p ){
		q = h2->head->next;
		while( q ){
			a = p->coef * q->coef;
			b = p->expo + q->expo;
			sw = 0;
			for( sentence *i = h3.head; i ; i = i->next ){
				if( b == i->expo ){
					if( a + i->coef ){
						i->coef += a;
						sw = 1;
						break;
					}
				}
			}
			if( !sw )
				h3.add(a, b);
			q = q->next;
		}
		p = p->next;
	}
}

int main()
{
	poly a, b, c;
	int e, co, n;
	cin >> n;
	for( int i = 0; i < n; i ++ ){
		cin >> co >> e;
		a.add( co, e );
	}
	cin >> n;
	for( int i = 0; i < n; i ++ ){
		cin >> co >> e;
		b.add( co, e );
	}
	multiply(&a, &b, c);
	a.print();
	b.print();
	c.print();
	return 0;
}