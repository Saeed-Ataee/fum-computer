#include <stdio.h>

struct book{
	char name[20];
	char writer[20];
	int year;
	bool borrow;
};

int main(){
	char ch[20];
	scanf("%s",ch);
	
	FILE *input = fopen("book.dat","r+b");
	if(!input)
		return 0;
	
	while(!feof(input)){
		book b;
		fread(&b,sizeof(book),1,input);
		if(strcmp(b.name,ch) == 0){
			fseek(input,-1L*sizeof(book),seek_cur);
			b.borrow = 0;
			fwrite(&b,sizeof(book),1,input);
			break;
		} 
		
	}

}
