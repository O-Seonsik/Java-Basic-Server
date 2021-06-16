# Server architecture pattern

## 현재 상황
Dispatcher에서 Header 6bytes를 읽어들여 0x5001, 0x6001 2개의 프로토콜에 따라 각기 다른 역할을 수행한다.

Protocol을 새로 만들고 ex)0x7001을 추가하기 위해서는 Dispatcher(demultiplex)의 switch 구문에 case를 계속 추가해야 한다.

패턴을 활용해서 외부에 프로토콜을 정의한 파일을 통해 코드를 수정하지 않고 프로토콜을 추가하는 방법은 없을까?

## Reactor Pattern
- Reactor
    - Dispatcher
        - Demultiflexer
    - HandleMap
        - (Stirng, Handle)
    
Dispatcher에 있는 socket으로 데이터를 받으면, Dispatcher는 Demultiflexer로 데이터를 보내고,
Demultiflexer는 데이터의 Header를 추출한다.
이후 Handle맵에 있는 key(header),value(handler)를 통해
Handler에 Header를 제외한 Payload를 함께 전송하여 작업을 처리한다.


-> 이제 기능이 추가 될 때 마다 기존 Demultiflexer에 Switch문에 코드를 추가하지 않고
EventHandler를 등록하는 것만으로 기능을 추가할 수 있다.
