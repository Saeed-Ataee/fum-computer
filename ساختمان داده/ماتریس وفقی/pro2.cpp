#include <iostream>

using namespace std;

#define SIZE 10

struct position{
	int x, y;
};

position a[ 1000 ];
int counter;
bool sw = false;

char matrix[ SIZE + 1 ][ SIZE + 1 ];

void printMap(){
	for( int i = 0; a[ i ].x != -1; i ++ ){
		matrix[ a[ i ].x ][ a[ i ].y ] = '*';
	}
	for( int i = 0; i < SIZE; i ++ ){
		for( int j = 0; j < SIZE; j ++ ){
			cout << matrix[ i ][ j ] << " ";
		}
		cout << endl;
	}
}

bool alreadySeen( int x, int y ){
	for( int i = 0; a[ i ].x != -1; i ++ ){
		if( a[ i ].x == x && a[ i ].y == y )
			return true;
	}
	return false;
}

void f( int x, int y, int c ){
	if( x == y && x == SIZE - 1 ){
		sw = true;
		/*for( int i = 0; i < c; i ++ ){
			cout << "( " << a[ i ].x << " , " << a[ i ].y << " )" << endl;
		}*/
		printMap();
	}
	if( sw )
		return;
	if( x > SIZE - 1 )
		return;
	if( y > SIZE - 1 )
		return;
	if( x < 0 )
		return;
	if( y < 0 )
		return;
	
	if( matrix[ x + 1 ][ y ] == '0' && !alreadySeen(x + 1, y) ){
		a[ c ].x = x + 1;
		a[ c ].y = y;
		f( x + 1, y, c + 1 );
	}
	a[ c ].x = a[ c ].y = -1;
	if( matrix[ x ][ y + 1 ] == '0' && !alreadySeen(x, y + 1) ){
		a[ c ].x = x;
		a[ c ].y = y + 1;
		f( x, y + 1, c + 1 );
	}
	a[ c ].x = a[ c ].y = -1;
	if( matrix[ x ][ y - 1 ] == '0' && !alreadySeen(x, y - 1) ){
		a[ c ].x = x;
		a[ c ].y = y - 1;
		f( x, y - 1, c + 1 );
	}
	a[ c ].x = a[ c ].y = -1;
	if( matrix[ x - 1 ][ y ] == '0' && !alreadySeen(x - 1, y) ){
		a[ c ].x = x - 1;
		a[ c ].y = y;
		f( x - 1, y, c + 1 );
	}
	a[ c ].x = a[ c ].y = -1;
}

int main(){
	counter = 0;
	for (int i = 0; i < 1000; i ++){
		a[ i ].x = a[ i ].y = -1;
	}
	a[ 0 ].x = a[ 0 ].y = 0;
	for( int i = 0; i < SIZE; i ++ ){
		for( int j = 0; j < SIZE; j ++ ){
			cin >> matrix[ i ][ j ];
		}
	}
	f( 0, 0, 1 );
	return 0;
}