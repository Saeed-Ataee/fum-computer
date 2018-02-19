#include <math.h>
#include <iostream>
#include <stdio.h>

using namespace std;

int main(){
	int x;
	cin >> x; 
	int temp = x;
	if(temp<0)
		temp = -temp;
	
	int size = (int)log10(temp) +1;
	char ch[100] = {'\0'};
	if(x<0){
		size++;
		ch[0] = '-';
	}
	
	for(int i=size-1;i>=0 && temp;i--){
		 ch[i] = temp%10 + '0';
		 temp/=10;
	}
	
	printf("%s\n",ch);
	
}
