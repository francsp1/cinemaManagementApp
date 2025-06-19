package model;

import java.io.Serializable;

public class Test implements Serializable {
    public static void main(String[] args) {

        var a = new A();

        var b = new B(a);
        var c = new C(a);

    }
}

class A implements Serializable {
    private static final long serialVersionUID = 1L;
}

class B implements Serializable {
    private static final long serialVersionUID = 1L;
    private A a;

    public B(A a) {
        this.a = a;
    }
}

class C implements Serializable {
    private static final long serialVersionUID = 1L;
    private A a;

    public C(A a) {
        this.a = a;
    }
}

