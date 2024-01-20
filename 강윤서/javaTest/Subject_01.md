## 과목평가 1회차. 예상문제 - 강윤서

#### 1. 다음 중 맞는 것을 고르시오.

    (1) 배열은 선언과 동시에 크기가 결정된다.

    (2) 배열의 크기를 후에 변경할 수 있다.

    (3) 배열의 크기를 구하는 방식은 String 타입의 크기(길이)를 구하는 방식과 동일하다.

    (4) 배열 안에 데이터 타입이 다른 값을 넣을 수 있다.

#### 2. 오버로딩의 정의와 장점에 대해 작성하시오.

#### 3. 다음 중 틀린 것을 고르시오.

    (1) class의 구성 요소는 변수, 함수, 생성자이다.

    (2) 생성자를 정의하지 않으면 생성되지 않는다.

    (3) 생성자는 객체가 생성될 때 호출된다.

    (4) static 함수에서는 this를 사용할 수 없다.

#### 4. 객체지향 프로그래밍의 특징 4가지를 작성하고, 객체지향 프로그래밍의 장점을 작성하시오.

#### 5. 제한자에 대한 설명으로 잘못된 것을 고르시오.

    (1) 제한자는 여러가지를 사용할 수 있으나 접근 제한자는 하나만 사용할 수 있다.

    (2) static으로 선언된 메소드에서는 static 메소드만 호출할 수 있다.

    (3) final로 선언된 변수나 메소드를 재정의할 수 있다.

    (4) private으로 선언된 클래스도 상속할 수 있다.

#### 6. 다음 코드의 예상 결과를 입력하시오.

```java
public class Main {
    int a;
    static int sa;
    public Main() {
        System.out.println("생성자");
    }
    public Main(int i) {
        System.out.println("int 생성자");
        a = i;
        sa = 250;
    }
    public static void main(String[] args) {
        Main m1 = new Main();
        m1.a = 100;
        m1.sa = 200;

        Main m2 = new Main(500);
        System.out.println(m1.a);
        System.err.println(m1.sa);
        Main.sa = 300;
        System.out.println(m2.a);
        System.out.println(m2.sa);
    }
}
```
