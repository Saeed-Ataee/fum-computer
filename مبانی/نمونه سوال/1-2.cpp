
const int n = 100; // moshtari
const int m = 20; // shoabe
int getBest(int sell[m][n]){

	int number = 0;
	int shoabe[m]= {0};
	for(int i=0;i<n;i++){
		int main =0;
		int max = -1;
		for(int j=0;j<m;j++){
			if(max<sell[j][i]){
				max = sell[j][i];
				main = j;
			}
		}
		shoabe[main]++;
	}
	int max = -1;
	for(int i=0;i<m;i++){
		if(shoabe[i]>max){
			max = shoabe[i];
			number = i;
		}
	}

	return number;
}