void printBool(bool v){
    if (v){
        println("True");
    }else{
        println("False");
    }
}
int main(){
    string s1 = "ACM";
    string s2 = "ACMilan";
    string s3 = "ACMClass";
    string s4 = s2.substring(0,2);

    printBool(s1 == s4);
    printBool(s1 != s4);
    printBool(s1 <  s4);
    printBool(s1 >  s4);
    printBool(s1 <= s4);
    printBool(s1 >= s4);

    println("");

    printBool(s1 == s2);
    printBool(s1 != s2);
    printBool(s1 < s2);
    printBool(s1 > s2);
    printBool(s1 <= s2);
    printBool(s1 >= s2);

    println("");

    printBool(s2 == s3);
    printBool(s2 != s3);
    printBool(s2 < s3);
    printBool(s2 > s3);
    printBool(s2 <= s3);
    printBool(s2 >= s3);

return 0;
}

