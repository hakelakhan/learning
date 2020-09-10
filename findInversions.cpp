#include <bits/stdc++.h>
using namespace std;
int inversions = 0;
void merge(int* a, int start, int mid, int end) {
	int i, j, k;
	//Before merging identify no of inversions
	for(int i = start, j = mid + 1; i <= mid && j <= end; ) {
		if(a[i] > a[j]) {
			inversions+=  mid - i + 1; 
			j++;
		}
		else {
			i++;
		}
	}
	int* sorted;
	sorted = new int[end - start + 1];
	for(i = start, j = mid + 1 ,k = 0; i <= mid && j <= end;) {
		if(a[i] < a[j]) {
			sorted[k++] = a[i++];
		}
		else if( a[j] < a[i]) {
			sorted[k++] = a[j++]; 
		}
		else {
			sorted[k++] = a[i++];
			sorted[k++] = a[j++];
		}
	}
	if(i > mid) {
		while(j <= end) {
			sorted[k++] = a[j++];
		}
	}
	else if(j > end) {
		while(i <= mid) {
			sorted[k++] = a[i++];
		}
	}	
	for(i = start, k = 0; i <= end; i++, k++) {
		a[i] = sorted[k];
	}
	delete sorted;
}
void mergesort(int* a, int start, int end) {
	int mid;
	if(start < end) {
		mid = (start + end) / 2;
		mergesort(a, start, mid);
		mergesort(a, mid + 1, end);
		merge(a, start, mid, end);
	}
}
void printArray(int* a, int n) {
	for(int i = 0; i < n; i++) 
		cout<<a[i]<<" ";
	cout<<endl;
}
int main() {
	int n, i;
	int* a;
	cout<<"How many numbers you want to enter  "<<endl;
	cin>>n;
	a = new int[n];
	cout<<"Start entering numbers   :   ";
	for(i = 0; i < n; i++) {
		cin>>a[i];
	}
	printArray(a, n);
	mergesort(a, 0, n - 1);
	printArray(a, n);
	cout<<"No of inversions = "<<inversions<<endl;
	delete a;
	return 0;
}
