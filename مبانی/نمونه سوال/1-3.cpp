#include <string.h>

int replace(char ch1[],char ch2[],char ch3[]){
	int len2 = strlen(ch2);
	int len3 = strlen(ch3);
	int index = strstr(ch1,ch2);
	int i=0;
	if(index==-1)
		return -1;
	for(i=0;i<len3 && i<len2;i++){
		ch1[i+index] = ch3[i];
	}
	if(len2>len3){
		for(;i<len2;i++){
			ch1[i+index] = ' ';
			shiftL(ch1,i+index);
		}
	}
	else if(len2<len3){
		for(;i<len3;i++){
			shiftR(ch1,i+index);
			ch1[i+index] = ch3[i];
		}
	}
}

void shiftR(char ch[],int index){
	for(int i=strlen(ch);i>=index;i--)
		ch[i+1] = ch[i];	
}

void shiftL(char ch[],int index){
	for(int i=index;i<strlen(ch);i++){
		ch[i-1] = ch[i];
	}
}
int strstr2(const char str1[], const char str2[]) {
   int i, j ;
 
   for (i=0; str1[i]; i++) 
       if (str1[i] == str2[0]) {
           for (j=1; str2[j] && str1[i+j] == str2[j] ; j++) ;
           if (!str2[j]) return(i);
       }
   return(-1) ;
}
