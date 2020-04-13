/**
 * 당첨 번호를 담당하는 객체
 */
class WinningLotto {
  lotto = null
  bonusNo = 0

  constructor(lotto, bonusNo) {
    this.lotto = lotto
    this.bonusNo = bonusNo
  }

  match(userLotto) {
    let count = 0
    let bonusCount = 0
    this.lotto.numbers
    this.lotto.numbers.map((number) => {
      userLotto.numbers.indexOf(number) >= 0 && count++
    })
    userLotto.numbers.indexOf(this.bonusNo) >= 0 && bonusCount++
    if (count === 6) return 1
    if (count === 5 && bonusCount) return 2
    if (count + bonusCount === 5) return 3
    if (count + bonusCount === 4) return 4
    if (count + bonusCount === 3) return 5
    return -1
  }
}
