int tak(int x, int y, int z) {
	if ( y < x ) return 1 + tak( tak(x-1, y, z), tak(y-1, z, x), tak(z-1, x, y) );
	else return z;
}

int main(){
	int a;
	int b;
	int c;
	a=getInt();
	b=getInt();
	c=getInt();
	println(toString(tak(a,b,c)));
	return 0;
}
