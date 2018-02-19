

void swap(int *a,int *b){
	if(*a<*b){
		int temp = *a;
		*a = *b;
		*b = temp;
	}
}

int main(){
	int x = 10,y = 15;
	swap(&x,&y);
}
