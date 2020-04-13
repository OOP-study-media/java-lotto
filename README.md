# java-lotto

로또게임 저장소

https://taenykim.github.io/java-lotto/

## 구현해야할 기능 목록

1. 구입하는 로또 개수 찾기 (인풋에러체크까지)

```ts
getLottoCount(price: number) : number
```

2. Lotto배열에 lottoCount수만큼 Lotto객체 담기

```ts
setLotto(lottoCount: number, lottos: Lotto[]): void
```

3. 랜덤 로또 배열 생성하기

```ts
setRandomNumbers(): number []
```

4. 로또번호, 보너스볼에 대한 에러체크

```ts
checkInputValue(lottos: string, bonusBall: string): boolean
```

5. 내 로또들과 winning로또 비교하기

```ts
matchLottos(myLottos: Lotto[], winningLotto: WinningLotto): void
```

6. 로또 하나와 winning로또 비교

```ts
Winning.match(userLotto: Lotto): number
```
