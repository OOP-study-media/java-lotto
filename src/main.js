const { body } = document
const LOTTO_MAX_NUMBER = 45
const lottoNumbers = []
for (let i = 0; i < LOTTO_MAX_NUMBER; i++) {
  lottoNumbers.push(i + 1)
}
const myLottos = []
const obj = {
  FIRST: 0,
  SECOND: 0,
  THIRD: 0,
  FOURTH: 0,
  FIFTH: 0,
  MISS: 0,
}

const makeDescription = (string, parrentElem) => {
  const description = document.createElement('div')
  description.innerHTML = `${string}`
  parrentElem.append(description)
}

const getLottoCount = (price) => {
  return price % 1000 === 0 ? price / 1000 : false
}

const setLotto = (lottoCount, lottos) => {
  const LOTTO_LENGTH = 6
  for (let i = 0; i < lottoCount; i++) {
    lottoArr = [...lottoNumbers]
    lottoTempArr = []
    for (let j = 0; j < LOTTO_LENGTH; j++) {
      const randomNumber = Math.floor(Math.random() * (LOTTO_MAX_NUMBER - j))
      const chosen = lottoArr.splice(randomNumber, 1)[0]
      lottoTempArr.push(Number(chosen))
    }
    lottoTempArr.sort((a, b) => a - b)
    lottos[i] = new Lotto(lottoTempArr)
  }
}

const matchLotto = (myLottos, winningLotto) => {
  for (let i = 0; i < myLottos.length; i++) {
    if (winningLotto.match(myLottos[i]) === 1) {
      obj['FIRST']++
    } else if (winningLotto.match(myLottos[i]) === 2) {
      obj['SECOND']++
    } else if (winningLotto.match(myLottos[i]) === 3) {
      obj['THIRD']++
    } else if (winningLotto.match(myLottos[i]) === 4) {
      obj['FOURTH']++
    } else if (winningLotto.match(myLottos[i]) === 5) {
      obj['FIFTH']++
    } else {
      obj['MISS']++
    }
  }
}

const priceForm = document.createElement('form')
body.append(priceForm)

makeDescription('구입 금액을 입력해주세요. ', priceForm)

const priceInput = document.createElement('input')
priceInput.type = 'text'
priceInput.style.width = '500px'
priceForm.append(priceInput)

const priceButton = document.createElement('button')
priceButton.type = 'submit'
priceButton.textContent = '입력!'
priceForm.append(priceButton)

const conditionForm = document.createElement('form')
conditionForm.style.display = 'none'
body.append(conditionForm)

makeDescription('지난주. ', conditionForm)

const lottoNumberInput = document.createElement('input')
lottoNumberInput.type = 'text'
lottoNumberInput.style.width = '500px'
conditionForm.append(lottoNumberInput)

makeDescription('보너스볼. ', conditionForm)

const bonusBallInput = document.createElement('input')
bonusBallInput.type = 'text'
bonusBallInput.style.width = '500px'
conditionForm.append(bonusBallInput)

const conditionButton = document.createElement('button')
conditionButton.type = 'submit'
conditionButton.textContent = '입력!'
conditionForm.append(conditionButton)

priceForm.addEventListener('submit', (e) => {
  e.preventDefault()
  if (!getLottoCount(priceInput.value)) {
    makeDescription('에러. ', priceForm)
    return
  }
  const lottoCount = getLottoCount(priceInput.value)
  setLotto(lottoCount, myLottos)
  let result = ''
  makeDescription(`${lottoCount}개를 구매했습니다.`, priceForm)
  for (let i = 0; i < lottoCount; i++) {
    console.log(myLottos[i])
    result += `${myLottos[i].numbers}<br/>`
  }
  makeDescription(`${result}`, priceForm)
  conditionForm.style.display = 'block'
})

conditionForm.addEventListener('submit', (e) => {
  e.preventDefault()
  const winningLotto = new WinningLotto(
    new Lotto(lottoNumberInput.value.split(',').map((item) => Number(item))),
    Number(bonusBallInput.value)
  )
  matchLotto(myLottos, winningLotto)
  console.log(obj)
})
