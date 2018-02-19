#include <iostream>

using namespace std;

class node{
public:
	int info;
	node *next;
};

class Linking_List{
public:
	Linking_List();
	void insert_sorted( int );
	void delete_n( int );
	void insert_to_last( int );
	void delete_from_last( int& );
private:
	node *first, *last;
};

Linking_List::Linking_List(){
	first = NULL;
	last = NULL;
}

void Linking_List::insert_sorted( int n ){
	node *p, *q, *temp;
	p = new node;
	q = new node;
	temp = new node;
	p->info = n;
	p->next = NULL;
	temp = first;
	while( temp && temp->info < n ){
		q = temp;
		temp = temp->next;
	}
	if( temp == first ){
		p->next = first;
		last = p;
	}else{
		p->next = q->next;
		q->next = p;
	}
}

void Linking_List::insert_to_last( int n ){
	node *p;
	p = new node;
	p->info = n;
	p->next = NULL;
	if( !first && first == last ){
		first = p;
		last = p;
	}else{
		last->next = p;
		last = p;
	}
}

void Linking_List::delete_n( int n ){
	node *temp, *q;
	temp = first;
	q = first;
	if( !first && last == first ){
		cout << "the list is empty !\n";
	}else{
		while( temp && temp ->info != n ){
			q = temp;
			temp = temp->next;
		}
		if( !temp ){
			cout <<"number not found !\n";
		}else{
			if( q == temp ){
				first = q->next;
			}else
				q->next = temp->next;
		}
	}
}

void Linking_List::delete_from_last( int& x ){
	node *temp;
	temp = first;
	if( !first )
		return;
	if( !temp ->next ){
		x = first->info;
		first = NULL;
		last = NULL;
		return;
	}
	while( temp->next->next ){
		temp = temp->next;
	}
	last = temp;
	x = temp->next->info;
	temp->next = NULL;
}

int main()
{
	Linking_List l;
	l.insert_to_last(5);
	l.insert_to_last(51);
	l.insert_to_last(512);
	l.insert_to_last(5125);
	l.delete_n(555);
	return 0;
}