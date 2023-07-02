# BDD with DCI pattern

## Why?
BDD를 GWT(given when then)으로 작성하는 방법도 있는데, 이 방법은 테스트 행위의 강제성을 부여할 수 없고, (given, when, then을 주석으로 관리) 테스트 결과를 보기 힘들다는 단점이 있다.

## What?
테스트케이스 자체가 요구사항이 되도록 소스코드를 작성하는 방법

## How?

- Describe : 설명할 테스트의 대상 `ex. Sum함수는` 
- Context : 테스트 대상이 놓인 상황 `ex. 300과 200이 주어지면,`
- It : 테스트 대상의 행동 `ex. 500을 반환된다.`

## Kata scenario

- http://codekata.com/kata/kata01-supermarket-pricing/

This kata arose from some discussions we’ve been having at the DFW Practioners meetings. The problem domain is something seemingly simple: pricing goods at supermarkets.

Some things in supermarkets have simple prices: this can of beans costs $0.65. Other things have more complex prices. For example:

three for a dollar (so what’s the price if I buy 4, or 5?)
$1.99/pound (so what does 4 ounces cost?)
buy two, get one free (so does the third item have a price?)
This kata involves no coding. The exercise is to experiment with various models for representing money and prices that are flexible enough to deal with these (and other) pricing schemes, and at the same time are generally usable (at the checkout, for stock management, order entry, and so on). Spend time considering issues such as:

does fractional money exist?
when (if ever) does rounding take place?
how do you keep an audit trail of pricing decisions (and do you need to)?
are costs and prices the same class of thing?
if a shelf of 100 cans is priced using “buy two, get one free”, how do you value the stock?
This is an ideal shower-time kata, but be careful. Some of the problems are more subtle than they first appear. I suggest that it might take a couple of weeks worth of showers to exhaust the main alternatives.
