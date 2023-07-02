# Protocol Buffers

@translated by xb   
이 글은 protocol buffer 공식 문서를 보며 사용법을 번역, 정리한 글이다.   
원문 : [Language Guide  |  Protocol Buffers  |  Google Developers](https://developers.google.com/protocol-buffers/docs/proto)
   
proto2를 번역하고 proto3에서의 변경점을 정리한 글이며, 의역과 오역이 다수 존재할 수 있다.   
   
사용법이 아닌 protocol buffer의 개요와 자바에서의 사용법및 직렬화된 데이터와 직렬화되지않은 데이터의 크기차이 테스트를 보고싶다면 [블로그](https://dlwnsdud205.tistory.com/328)를 참조하도록 하자.   
   
### 목차 
> #### proto2
> 1. [Protocol Buffer fields 사용 예시 (Protocol Buffer fields)](#protocol-buffer-fields)
> 2. [proto2의 단일 값들 (Scalar value types of proto2)](#scalar-value-types-of-proto2)
> 3. [여러개의 messages를 하나의 .proto 파일에서 사용하기(Multiple messages)](#multiple-messages)
> 4. [삭제된 식별자와 필드 재사용 금지 (Reserved field)](#reserved-field)
> 5. [필드의 기본값 설정 (Default values)](#default-values)
> 6. [열거형 (Enumerations)](#enumerations)
> 7. [다른 message타입을 필드 타입으로 사용하기](#다른-message타입을-필드-타입으로-사용하기)
> 8. [중첩 message 유형](#중첩-message-유형)
> 9. [Groups-(deprecated)](#groups-deprecated)
> 10. [기존에 존재하는 code 수정 없이 message 업데이트하기](#기존에-존재하는-code-수정-없이-message-업데이트하기)
> 11. [필드 식별자 예약어 (Extension)](#extensions)
> 12. [여러개의 값중에 하나를 선택하기 (Oneof)](#oneof)
> 13. [Maps](#maps)
> 14. [Packages](#packages)
> 15. [다른 서비스와 연관해 사용하기 (ex. Remote Procedure Call)](#다른-서비스와-연관해-사용하기-ex.-remote-procedure-call)
> 16. [Options](#Options)
> 17. [.proto 파일을 컴파일해서 class로 만들기](#proto-파일을-컴파일해서-class로-만들기)
> > #### proto3
> > 1. [proto3와 proto2의 차이](#proto3와-proto2의-차이)
> > 2. [proto2에서 proto3로 변경하기](#proto2에서-proto3로-변경하기)
> > 3. [proto3의 필드 선언 방식과 단일값들 (proto3 - fields)](#proto3---fields)
> > 4. [proto3 - JSON Mapping](#proto3---json-mapping)
   
### Protocol Buffer fields

```protobuf
message SearchRequest{   
	required string query = 1;    
	optional int32 page_number = 2;    
	optional int32 result_per_page = 3    
}
```
   
SearchRequest는 name-value 쌍으로 이루어진 3개의 필드로 이루어져있다.   
아래 예제는 단일값 (scalar type)으로 만 이루어져 있지만 enumerations을 사용해서 composite 타입도 정의 할 수 있다.    
또한, 필드를 보면 각 필드는 unique한 식별자로 이루어져 있는걸 볼 수 있는데, 이는 메시지의 이진 형식 필드를 식별하는데 사용되며 메시지가 한번 이상 사용되었다면 바뀌면 안된다. 메시지 식별자 1에서 부터 15번은 인코딩 하는데 1byte 가 필요하며 16에서 2047은 2byte가 필요하다. 따라서, 자주 사용되는 메시지 요소에 대해서 1에서 15번의 식별자를 예약해야한다. 식별자는 1 에서 2^29-1 만큼 예약할 수 있다. 또한, 식별자로 19000 에서 19999는 사용 할 수 없는데, 이는 protocol Buffer의 예약어 이기 때문이다.   
   
message 필드는 아래의 요소들로 이루어진다.
``` 
required : 메시지 타입은 이 요소가 정확히 하나 있어야한다. required타입의 필드는 메시지에서 반드시 전송되어야한다.
optional : 메시지 타입은 이 요소가 없거나 여러개 있을수있다. optional타입의 필드는 전송될수도 안될수도 있다.
repeated : 이 타입은 여러번 반복 가능하며, 반복되는 값의 순서는 유지된다.
```
   
또한, 단일값 타입의 repeated 필드(int32, int64, enum)은 효과적으로 인코딩 되지않는데, 이 를 방지하기 위해 [packed = true] 옵션을 줘야한다.

```protobuf
	repeated int32 samples = 4 [packed = true];
	repeated ProtoEnum results = 5 [packed = true];
```
    
required타입의 필드는 특히 조심히 작성해햐하는데, required필드를 보내고싶지 않아서 optional로 바꾼경우, 바뀐사실을 모르는 수신자는 required필드가 비어있다고 생각하고 잘못된 요청으로 인식해 이를 drop할 것이다.   
   
### Scalar value types of proto2
다음은 .proto 파일에서 사용할 수 있는 단일 타입이다. .proto type이 java로 변환되었을때만을 정리했다. 만약 다른 언어를 사용한다면 공식문서를 타고 들어가서 확인하자.   
공식문서 : [Language Guide  |  Protocol Buffers  |  Google Developers](https://developers.google.com/protocol-buffers/docs/proto#scalar)   
   
| .proto type | java type | Note |
| --- | --- | --- |
| double | double | |
| float | float | |
| int32 | int | 음수를 인코딩할때 비효율적이다. 만일 필드가 음수가 될 수 있다면, sint32를 사용하도록 하자. |
| int64 | long | 음수를 인코딩할때 비효율적이다. 만일 필드가 음수가 될 수 있다면, sint64를 사용하도록 하자. |
| uint32 | int | |
| uint64 | int | |
| sint32 | int | 음수 인코딩에 효율적이다. |
| sint64 | long | 음수 인코딩에 효율적이다. |
| fixed32 | int | 항상 4바이트이며, 값이 종종 2^28보다 클 경우 uint32보다 효율적이다. |
| fixed64 | long | 항상 8바이트이며, 값이 종종 2^56보다 클 경우 uint64보다 효율적이다. |
| sfixed32 | int | 항상 4바이트이며, 음수가 2^28보다 작을 경우 sint32보다 효율적인듯 하다. |
| sfixed64 | long | 항상 8바이트이며, 음수가 2^56보다 작을 경우 sint64보다 효율적인듯 하다. |
| bool | boolean | |
| string | String | UTF-8 encoding된 text이다. |
| bytes | ByteString | |   
   
### multiple messages 
또한, 여러개의 메시지를 하나의 .proto file에 정의할 수 있는데, 이는 연관된 메시지를 표현하는데 효과적이다.  예시는 아래와 같다.   
```protobuf
message SearchRequest{
	required string query = 1;
	optional int32 page_number = 2;
	optional int32 result_per_page = 3;
}

message SearchResponse{
	required string query = 1;
	optional int32 page_number = 2;
	optional int32 result_per_page = 3;
}
```
   
.proto 파일에는 가능한한 적은 메시지를 포함하는게 좋은데, 많은 메시지를 포함할 경우 의존성이 증가할 수 있다.   
   
### reserved field
.proto 파일의 필드를 없애거나 주석처리한 후, 나중에 해당 식별자를 재사용할 경우 이는 심각한 에러를 야기 할 수 있다. 문제는 protoc 컴파일러는 이 를 잡아주지 않는다는 것인데, 이 상황을 막기 위해서, reserved 필드를 사용할 수 있다.   
```protobuf
message Foo{
	reserved 2, 15, 9 to 11;
	reserved "foo", "bar";
}
```
   
### Default Values
message에 optional 필드는  Default value를 줄 수있는데, Default value를 줄 경우, parsing된(객체로 변환된) message에 접근하면 Default value를 얻을 수 있다.      예시는 다음과 같다.   
```protobuf
message SearchRequest{
	required string query = 1;
	optional int32 page_number = 2;
	optional int32 result_per_page = 3 [default = 10];
}
```
   
### Enumerations
Enum 타입의 형식을 선언하고 싶을때, enum을 사용할 수 있다.  예시는 다음과 같다.
```protobuf
enum Corpus{
	CORPUS_UNSPECIFIED = 0;
	CORPUS_UNIVERSAL = 1;
	CORPUS_WEB = 2;
	CORPUS_IMAGES = 3;
	CORPUS_LOCAL = 4;
	CORPUS_NEWS = 5;
	CORPUS_PRODUCTS = 6;
	CORPUS_VIDEO = 7;
}

message ExampleMessage{
	required string someField = 1;
	optional Corpus corpus = 2 [default = CORPUS_UNSPECIFIED];
}
```
   
위 예시를 보면, Corpus Enum타입에 선언된 값중 하나를 사용할 수 있다. 또한, Enum 내부에 식별값으로 같은 값을 사용하고 싶을 수 있는데, 이 를 위해선 allow_alias option을 true로 주면 된다. 예시는 아래와 같다.   
```protobuf
enum Corpus{
	option allow_alias = true;
	CORPUS_UNSPECIFIED = 1;
	CORPUS_UNIVERSAL = 1;
}
```
   
또한, protoc compiler는 enum타입을 Java, C++의 enum에 상응하게 컴파일 해준다.    
Enum또한 마찬가지로 reserved values를 사용해야하며 사용하는것이 권장된다.
```protobuf
enum Foo{
	reserved 2, 15, 9 to 11, 40 to max;
	reserved "FOO", "BAR";
}
```

### 다른 message타입을 필드 타입으로 사용하기
다른 message타입을 필드 타입으로 사용하는 2가지 방법이 있는데, 첫번째는 message 타입이 같은 .proto 파일에 존재하는 경우고, 두번째는 message타입이 다른 .proto 파일에 존재하는 경우이다.   
우선, 사용하려는 message타입이 같은 .proto파일에 존재하는 경우의 예시를 보자.   
```protobuf
message SearchResponse{
	repeated Result result = 1;
}

message Result{
	required string url = 1;
	optional string title = 2;
	repeated string snippets = 3;
}
```
   
message타입이 다른 .proto 파일에 존재한다면, import를 사용해 가져와 사용할 수 있다.   
```protobuf
import "myproject/other_protos.proto";
```
이런식으로 경로를 지정해 사용할 수 있으며, 만일 경로가 변경되는게 예상될 경우 import public 문을 사용해 상대경로? 를 지정해줄 수 있는거 같은데, 이는 Java에서는 호환되지 않는다고 한다. 따라서 따로 정리하지는 않겠고 이 부분에 대해선 공식 문서를 참조하도록 하자.   

### 중첩 message 유형
또한, message는 중첩이 가능한데, (객체 안의 객체 느낌) 사용법은 아래와 같다.   
```protobuf
message Outer{
	message MiddleA{
		message Inner{
			optional int64 ival = 1;
			optional bool booly = 2;
		}
	}
	message MiddleB{
		message Inner{
			optional string name = 1;
			optional bool flag = 2;
		}
	}
}
```
   
위 예시를 보면, 중첩 message안의 필드 (message를 포함한)는 각각 독립적인 message로 취급되는것을 알 수 있다. message MiddleA 안의 Inner와 message MiddleB안의 Inner 는 각각 같은 message name을 갖고있음에도 오류가 발생하지 않으며, 각 Inner message의 필드의 식별자 또한 중복이 가능하다.   
   
이런 중첩 message를 다른 message에서 사용하려 할 경우 다음과 같이 사용하면 된다.
```protobuf
message Client{
	optional Outer.MiddleA.Inner outerMiddleAInner = 1;
}
```
   
### Groups (deprecated)
Groups라고 불리는 타입이 존재했는데, 이제 deprecated되었고 더이상 새로운 message타입으로 사용하면 안된다고 한다. 대신에 중첩 message타입 사용을 권장하며, 문서를 훑어본 결과 사용법과 용도가 중첩 message타입과 유사하다. 
   
### 기존에 존재하는 code 수정 없이 message 업데이트하기
기존의 message타입을 업데이트 (새로운 필드의 추가 등)을 하고 싶은데, 존재하는 코드를 수정하고 싶지 않을 수 있다. 이 경우 아래 규칙을 따르면 된다.   

> 단, JSON이나 proto text format을 protocol buffer message를 인코딩하는데 사용한다면 다른 방법을 사용해야 한다. (아래는 원문)
> **Note:** If you use JSON or proto text format to store your protocol buffer messages, the changes that you can make in your proto definition are different.
   
이제 규칙을 살펴보자.   
1. 기존에 존재하는 필드의 식별자를 수정해선 안된다.
2. 새로 추가되는 필드는 *optional* 이거나  *repeated* 이여야 한다. (기존에 존재하는 코드에 required가 존재할 것 이므로)
3. required가 아닌 fields들은 삭제할 수 있다. 단, 반드시 업데이트 되는 message에 이전에 삭제된 fields들의 식별자를 재사용 하지 않도록 주의하자. (이 경우, reserved를 반드시 사용해서 식별자 재사용을 막자)
4. required가 아닌 field들은 타입과 식별자가 같은 한, extension타입으로 변경이 가능하며, 반대로 extension에서도 field타입으로 변경이 가능하다. 
5. int32, uint32, int64, uint64, bool 타입들은 서로 호환된다. 즉 이 타입들은 서로 변경 가능하다.
6. sint32, sint64는 서로 호환된다. 
7. string, bytes는 서로 호환된다. (단, bytes가 유효한 UTF-8이여야 한다.)
8. fixed32는 sfixed32와 호횐되고, fixed64는 sfixed64와 호환된다.
9. string, bytes, message타입에 한해서 optional은 repeated와 호환된다.
10. default values를 변경하는것은 괜찮다.
11. enum은 int32, uint32, int64, uint64와 호횐되지만, 값이 맞지 않으면 잘린다. 또한, client 는 이 둘을 다르게 역 직렬화 할 수 있으니 주의해야한다.  또한, Java와 C++에서 인식되지않은 enum필드는 다른 알 수 없는 값과 함께 저장되고 이 를 직렬화 한다음 역직렬화 하면 이상한 알 수 없는 에러가 발생할 수 있으니 주의해야한다.
12. 하나의 optional field를 새로운 oneof 로 변경하는것은 안전하고 이진 호환되며 여러개의 fields를 새로운 oneof로 변경하는것도 안전할 확률이 높다. 마찬가지로 하나의 oneof 를 optional이나 extension으로 변경하는것은 안전하다. 하지만, 존재하는 oneof에 fields를 옮기는것은 안전하지 않다. 
13. map<K, V> field를 repeated field로 변경하는것은 대게 안전하지만, 이는 application에 의존적이다. 다시말해 어떤 application에서는 안전하지 않을 수 있다.

   
### Extensions
extensions은 식별자 예약어(의역 : 원문은 placeholder이지만 표시자 라고 해석하기 보다는 식별자 예약어로 해석하는것이 extension의 목적에 더 어울린다고 생각한다. 이 부분은 오역일 가능성이 있기때문에 따로 남긴다.) 로  extensions을 extend한 message에서 extend한 message의 extensions의 범위를 사용할 수 있다. 글로 읽으면 어려우니 예시를 보자.   
```protobuf
message Foo{
	// some fields
	extensions 1 to 15;
}

extend Foo{
	optional int32 bar = 14;
}
```
   
또한, 이런 extension은 언어로 컴파일 되었을때 접근방법이 좀 다르다. 이 방법에 관해선 자세한 언급은 없었고, 각 언어에 맞는 컴파일된 결과를 참조하라 적혀있다.   
   
extensions또한, message처럼 중첩해서 사용가능하다. 다음은 예시이다. 
```protobuf
message Foo{
	// some fields
	extensions 1 to 15;
}

message Baz{
	extend Foo{
		optional Baz baz = 14;
	}
}
```
   
또한, extensions는 어떠한 필드의 타입도 될 수 있지만, oneofs와 maps는 될 수 없다. extnsions의 범위를 지정할때도 조심해야하는데, 예를들어 아래와 같이 컴파일러 예약어(19000 ~ 19999)를 범위에 포함한 경우 protoc 컴파일러는 에러를 내뿜을 것이다.
```protobuf
message Foo{
	// some fields
	extensions 1 to max;
}
```
   
### Oneof
message에 많은 optional fields가 있고, 이 fields들 중에 동시에 하나만이 설정된다면 oneof를 사용해서 메모리를 아낄 수 있다.   
   
oneof는 자신안의 fields들이 메모리를 공유하게 하여 메모리 사용을 아끼며, 동시에 하나의 값만 설정 가능하도록 강제한다. oneof안의 값을 설정하면 다른 모든 값들은 무시되며, 언어에따라 oneof에 설정된 값을 확인할 수 있는 정보를 제공한다.   
다음은 oneof 사용 예시이다.
```protobuf
message SmapleMessage{
	required id = 1;
	oneof testOneOf{
		string name = 2;
		int32 time = 3;
	}
}
```
   
위 예시를 보면, oneof필드에는 required, optional, repeated를 사용하지 않는것을 알 수 있다. 또한, oneof필드에는어떠한 값 이라도 추가할 수 있다. oneof 필드가 컴파일되면, optional과 마찬가지로 setter, getter가 추가된다. 
   
### Maps
key-value로 된 fields를 선언하고 싶을때, protocol buffer의 map을 사용할 수 있으며 기본적인 선언 방법은 아래와 같다.   
```protobuf
message MapExample{
	map<int, string> map = 1;
}
```
   
map의 key값에는 Enum타입을 제외한 스칼라 타입(int, string, etc…)이 올 수 있으며, value에는 map을 제외한 모든 타입이 올 수 있다.   
   
다음은 map의 특징이다
1. Extensions는 map을 지원하지 않는다.
2. map은 repeated, optional, required가 될 수 없다.
3. .proto를 이용해 text format을 만들시 map은 키값을 기준으로 정렬된다.
4. map타입에 중복키가 존재할때, 마지막 값을 기준으로 사용되지만, 타입이 text format이라면 duplicate key fail이 발생한다.
   
### Packages
protocol buffer의 message들 끼리의 이름 충돌을 피하기 위해서 package를 선언할 수 있다.
```protobuf
package foo.bar;
message Open{ ... }
```   
```protobuf
message Foo{
	required foo.bar.Open open = 1;
}
```
   
package 지정자는 언어에 따라 생성된 code에 영향을 미치는데, 자바의 경우, java package처럼 사용된다. 단, .proto file에 java package옵션이 있다면, java package옵션을 기준으로 생성된 코드의 패키지 경로가 지정된다.
   
### 다른 서비스와 연관해 사용하기 (ex. Remote Procedure Call)
protocol buffer를 다른 서비스 (rpc)와 함께 사용하기 위해선, .proto 파일에 rpc service를 정의해야한다. rpc service가 정의되어있다면 protoc compiler는 이 를 읽고, stub과 service interface code를 함께 생성 해준다. 예를들어, SearchRequest를 전송하면, SearchResponse를 응답받는 rpc서비스를 사용하고 싶다면, .proto파일에 아래와 같이 정의해야한다.
```protobuf
service SearchService{
	rpc Search(SearchRequest) returns (SearchResponse);
}
```
   
protoc 컴파일러는 위 파일을 읽고, SearchService 추상 인터페이스를 만든후, 그에 상응하는 stub을 구현한다. stub은 모든 요청을 RpcChannel로 보내며, RpcChannel은 사용자가 정의해야하는 RPC system이다. 만일 Rpc시스템(RpcChannel)을 구현하고 싶지 않다면, grpc를 사용할 수 있다. 만일 grpc를 사용하고 싶다면, protocol buffers version 3.0.0이상을 사용해야한다.
   
### Options 
.proto 파일은 options에 의해 annotated될 수 있다. option은 문서의 전체적인 의미를 변경하지는 않지만, 처리방식에 영향을 줄 수 있다.   
   
options은 file level, message level, field level이 있는데, file level은 message 밖에 선언되야하며, message level은 message안에 선언, field level은 field안에 선언되어야 한다.   
   
존재하는 모든 옵션은 아래 링크에서 에서 볼 수 있으며, 이 파일에는 일부의 option만 후술한다.
> https://developers.google.com/protocol-buffers/docs/reference/cpp/google.protobuf.descriptor.pb
   

자주 사용되는 옵션들   
* **java_package** (file option) : .proto파일에 의해 생성된 java 파일의 패키지 경로를 지정할 수 있다. 존재하지 않는다면, .proto파일의 package에 대응 되는 패키지로 java 파일이 생성된다. 
```protobuf
option java_package = "com.example.foo";
```
* **java_outer_classname** (file option) : .proto파일에 의해 생성된 java 파일의 class name과 file name을 지정해 줄 수 있다. 만약 존재하지 않는다면, .proto파일의 이름에 camel case를 적용해 자바 파일을 만든다.
```protobuf
option java_outer_classname = "Ponycopter";
```
* **java_multiple_files** (file option) : 하나의 .proto파일에 의해 생성된 java 파일을 한 파일에 전부 둘지, 여러개의 파일로 나눌지 선택하는 옵션이다. 기본값은 false이며, false라면, 하나의 .proto 파일을 한 자바 파일에 중첩시켜서 만들고 true라면 여러 파일에 나눠 만든다.
```protobuf
option java_multiple_files = true;
```
* **optimize_for** (file option) : Java와 C++코드를 생성할때 사용할 수 있으며, 선택가능한 옵션은 다음과 같다. 
> **SPEED** (default) : protoc 컴파일러는 직렬화, parsing을 비롯한 다양한 코드를 생성하며 고도로 최적화 되어있다.
> **CODE_SIZE** : protoc 컴파일러는 최소한의 class들만 생성하고, shared와 reflection에 의존한 직렬화, parsing을 구현한다. SPEED보다는 느리지만, 생성된 코드가 작다는 이점이 있다. 
> **LITE_RUNTIME** : protoc 컴파일러는 “lite” runtime library에 의존하여 클래스를 생성한다. lite runtime library는 직렬화, descriptors등과 같은 특정 기능을 생략하지만 lite가 아닌 라이브러리 보다 10배 작으며 휴대폰과 같은 제한된 플랫폼에서 유용하다.
* **packed** (field option) : repeated field에 선언가능한 boolean 타입이며, true로 설정된다면, 더 간결한 인코딩이 진행된다. 하지만 버전 2.3.0 이전에서 사용할 경우 문제가 발생할 수도 있다고 하니 버전에 조심해서 사용하자.
```protobuf
message Foo{
	repeated int32 samples = 4 [packed = true];
}
```
* **deprecated** (field option) : boolean타입이며, true일 경우 새로운 코드에서 더이상 이 field는 사용되지 않는다. 대부분의 언어에서는 효과가 없지만, Java의 경우 **@Deprecated**로 변경된다. 대체로 deprecated option을 선언하는것 보단 reserved를 사용하는것이 추천된다.
```protobuf
message Foo{
	optional int32 oldField = 1 [deprecated = true];
}
```
   
### .proto 파일을 컴파일해서 class로 만들기
.proto파일을 갖고 Java, C++, Python과 같은 프로그래밍 언어로 만들기 위해선, protoc 컴파일러로 컴파일을 해야한다. protoc 컴파일러는 다음과 같이 사용할 수 있다.
```powershell
protoc --proto_path=IMPORT_PATH --cpp_out=DST_DIR --java_out=DST_DIR --python_out=DST_DIR path/to/file.proto
```
   
* --proto_path는 .proto files들이 존재하는 경로이다. 생략이 가능하며 생략된다면 현재위치가 경로가 된다. 또한, 이 옵션을 여러번 지정하여 여러 경로를 대상으로 탐색 가능하다. 
* DST_DIR은 컴파일된 코드가 저장될 위치이다. 마지막을 .zip이거나 .jar로 설정가능하다. 
* path/to/file.proto는 proto파일의 경로와 이름이다. 앞에 설정한 proto_path값을 root 경로로 설정하여 찾는다.

   
### proto3와 proto2의 차이
> 여담 : protocol buffer를 공부하는 목적이 grpc를 사용하기 위함인데.. grpc에서는 proto3을 사용하는것이 좋다고 한다. (proto2를 사용해도 상관없다고는 함 다만, 버전을 3.0.0이상으로 올리라 되어있다) 근데 이 사실을 원문 번역이 거의 다 끝나갈때쯤 알게되었다. 그래서 추가로 proto3와 proto2의 차이를 간략하게 적도록 하려한다. proto3 원문을 대충 훑어보니 몇가지를 제외하곤 거의 일치하는것 같아 다행이다.

### proto2에서 proto3로 변경하기
proto3의 경우 첫번째 라인에 “syntax”구문을 추가하며 사용 가능하다. 만약 이 구문이 없다면, protoc 컴파일러는 proto2라고 인식할 것 이다.
```protobuf
syntax = "proto3";
```

### proto3 - fields
proto3의 경우 field에 repeated, required, optional을 지정하지 않아도 되며 repeated의 경우 지정해도 된다. 다만, required, optional의 경우 지정하는 모습이 보이지 않는것을 봐선 지정할 수 없는것으로 추정된다.   
즉, 다음과 같이 선언할 수 있다.
```protobuf
syntax = "proto3";

message Foo{
	int32 id = 1;
	string name = "xb"; 
}
```
추가로, proto3에서는 [default = ]식의 default값을 줄 수 없다.   
   
다음은 proto3에서 사용 가능한 scalar 타입들이다. 타입별 설명을 자세히 읽지는 않았지만 적어도 지원하는 타입은 proto2와 일치해 보인다. 타입별 설명또한 일치해 보이는거로 봐선 여기서 변경사항은 없는듯 하다.
[Language Guide (proto3)  |  Protocol Buffers  |  Google Developers](https://developers.google.com/protocol-buffers/docs/proto3#scalar)
   
주의. 아래 표는 위에 적은 proto2의 표이다.   
| .proto type | java type | Note |
| --- | --- | --- |
| double | double | |
| float | float | |
| int32 | int | 음수를 인코딩할때 비효율적이다. 만일 필드가 음수가 될 수 있다면, sint32를 사용하도록 하자. |
| int64 | long | 음수를 인코딩할때 비효율적이다. 만일 필드가 음수가 될 수 있다면, sint64를 사용하도록 하자. |
| uint32 | int | |
| uint64 | int | |
| sint32 | int | 음수 인코딩에 효율적이다. |
| sint64 | long | 음수 인코딩에 효율적이다. |
| fixed32 | int | 항상 4바이트이며, 값이 종종 2^28보다 클 경우 uint32보다 효율적이다. |
| fixed64 | long | 항상 8바이트이며, 값이 종종 2^56보다 클 경우 uint64보다 효율적이다. |
| sfixed32 | int | 항상 4바이트이며, 음수가 2^28보다 작을 경우 sint32보다 효율적인듯 하다. |
| sfixed64 | long | 항상 8바이트이며, 음수가 2^56보다 작을 경우 sint64보다 효율적인듯 하다. |
| bool | boolean | |
| string | String | UTF-8 encoding된 text이다. |
| bytes | ByteString | |      
   
### proto3 - JSON Mapping
아마 이 JSON Mapping 때문에, gprc에서 proto3를 사용하는것을 권장하는것 처럼 생각된다.   
   
proto3는 JSON에 대한 표준 인코딩을 지원하며, 이는 시스템간 데이터 공유를 더 쉽게 해준다. 아래표는 유형별 인코딩 표 이다.   

| proto3 | JSON | JSON example | Notes |
|---|---|---|---|
| message | object | {“fooBar” : v, “g” : null, …} | message field의 이름은 lowerCamelCase형태로 json objectdml key가 된다. 만일 json_name필드 옵션이 지정되면 지정된 값이 대신 키로 사용된다. |
| enum | string | "FOO_BAR" | enum에 지정된 이름이 사용된다. |
| map<K,V> | object | {"K" v, ...} | 모든 키가 string으로 변경된다.|
| repeated V | array | [v, ...] | null값은 비어있는 리스트가 된다. |
| bool | true, false | true, false | |
| string | string | "Hello World!" | |
| bytes | base64 string | "YWJjMTIzIT8kKiYoKSctPUB+" |  | 
| int32, fixed32, uint32 | number | 1, -10, 0 | |
| int64, fixed64, uint64 | string | "1", "-10" | 64의 경우 string으로 변환됨 |
| float, double | number | 1.1, -10.0, 0, "NaN", "Infinity" |
| Timestamp | string | "1972-01-01T10:00:20.021Z" | |
| Duration | string | "1.000340012s" , "1s"| |
| Struct | object | { ... } | |
| Wrapper types | various types | 2, "2", "foo", true, "true", null, 0, ... | |
| FieldMask | string | "f.fooBar, h" | |
| ListValue | array | [foo, bar, ...] | |
| Value | value |  |  |
| NullValue | null | | |
| Empty | object | {} | |

-----
@translate by xb   
이 외의 protocol buffers 사용 예시등은 https://dlwnsdud205.tistory.com/328 에서 확인할 수 있음.
