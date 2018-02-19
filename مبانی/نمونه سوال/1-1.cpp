#include <stdio.h>

struct rabete{
	int x,y;
};
struct majmoe{
	rabete rabet[1000];
	int length;
};
bool tagharon(majmoe m){
	
	for(int i=0;i<m.length;i++){
		rabete temp = m.rabet[i];
		bool sw = false;
		for(int j=0;j<m.length;j++){
			if(i==j)
				continue;
			if(m.rabet[j].x == temp.y && m.rabet[j].y== temp.x){
				sw = true;
				break;
			}
		}
		if(!sw)
			return false;
	}
	return true;
}