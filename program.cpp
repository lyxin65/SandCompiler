//让你们知道真正的残忍
int i = 0;
int a0 = 0;
int a1 = 0;
int a2 = 0;
int a3 = 0;
int a4 = 0;
int a5 = 0;
int a6 = 0;
int a7 = 0;
int a8 = 0;
int a9 = 0;
int a10 = 0;
int b0 = 0;
int b1 = 0;
int b2 = 0;
int b3 = 0;
int b4 = 0;
int b5 = 0;
int b6 = 0;
int b7 = 0;
int b8 = 0;
int b9 = 0;
int b10 = 0;

int main() {
    int sum = 0;
    int n = 300000000;
    for (i = 0; i < n; i = i + 1) {
        a0 = a0 + 1;
        a1 = a1 + 1;
        a2 = a2 + 1;
        a3 = a0 + 1;
        a4 = a1 + 1;
        a5 = a2 + 1;
        a6 = a0 + 1;
        a7 = a1 + 1;
        a8 = a2 + 1;
        a9 = a0 + 1;
        a10 = a1 + 0;
        b0 = a0;
        b1 = a1;
        b2 = a2;
        b3 = a3;
        b4 = a4;
        b5 = a5;
        b6 = a6;
        b7 = a7;
        b8 = a8;
        b9 = a9;
        b10 = a10;
        if (i % 10000000 == 0) sum = (sum + a0 + a1 + a2 + a3 + a4 + a5 + a6 + a7 + a8 + a9 + a10
            + b0 + b1 + b2 + b3 + b4 + b5 + b6 + b7 + b8 + b9 + b10) & (32767 << 16 | 32767);
    }
    println(toString(sum));
    return 0;
}

