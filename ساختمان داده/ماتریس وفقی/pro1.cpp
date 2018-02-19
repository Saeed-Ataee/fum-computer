#include <iostream>

using namespace std;

int main(){
	int n, i, j, p;

	cin >> n;
	if( !(n % 2 ) || n < 1 || n > 30 ){
		cout << "Not good input!" << endl;
		return 0;
	}
	int a[ 30 ][ 30 ];
	int x = 1;
	i = 1;
	p = (int)n/2;
	j = p + 1;
	while( x <= n * n ){
		a[ i ][ j ] = x;
		i --;
		j --;
		if( !(x % n) )
			i += 2;
		x ++;
		if( i == 0 )
			i = n;
		if( j == 0 )
			j = n;
	}
	for( int i = 1; i <= n; i ++ ){
		for( int j = 1; j <= n; j ++ ){
			cout << a[ i ][ j ] << "\t";
		}
		cout << endl;
	}
	cout << endl << "the sum of each row and column is : " << n * ( n * n + 1 ) / 2 << endl << endl;
	return 0;
}