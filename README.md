# Server architecture pattern

## 현재 상황
여러 접속자가 몰리고 굉장히 느린 접속이 발생하면 다른 접속도 모두 지연된다.

## 여러 일을 동시에 처리하기 위해서?
접속이 오면 스레드로 일을 처리해서 다른 접속을 할 수 있도록 한다.

그러나 매번 접속이 들어올 때 스레드를 생성하게 되면, 스레드가 무제한으로 늘어나게 된다.

Context Switching에도 엄청난 비용이 발생하여 서버가 전체적으로 느려지고 메모리에도 문제가 발생한다.

### ThreadPool 을 활용하자
- 접속이 들어오기 전에 미리 스레드를 만들어둠
    - 스레드 생성에 지연을 줄임
- 스레드의 개수를 제한하고 다 쓴 스레드를 재활용한다.
- 경우에 따라 ThreadPer 방식의 코드를 활용하여 Thread를 생성한다.

### 이전 구조
Reactor -> ThreadPerDispatcher(Demultiplex) -> Each Handler

### 개선 구조
Reactor -> Thread(Per or Pool)Dispatcher -> Thread(Demultiplexer) -> Each  Handler


