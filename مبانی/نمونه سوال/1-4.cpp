#include <stdio.h>

int main(){
	FILE * input;
	input = fopen("average.dat","r+b");
	if(!input)
		return -1;
	float ave = 0;
	int n = 0;
	while(!feof(input)){
		float temp;
		fread(&temp,sizeof(float),1,input);
		ave+=temp;
		n++;
	}
	ave/=n;
	fseek(input,0,SEEK_SET);
	while(!feof(input)){
		float temp;
		fread(&temp,sizeof(float),1,input);
		if(temp<ave){
			fseek(input,-1L*sizeof(float),SEEK_CUR);
			temp = 0;
			fwrite(&temp,sizeof(float),1,input);
		}
	}
}
