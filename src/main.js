const { body } = document
const LOTTO_MAX_NUMBER = 45
const lottoNumbers = []
for (let i = 0; i < LOTTO_MAX_NUMBER; i++) {
  lottoNumbers.push(i + 1)
}

let result = '<br/>실행결과<br/>'

const makeDescription = (string, parrentElem) => {
  const description = document.createElement('div')
  description.innerHTML = `${string}`
  parrentElem.append(description)
}

const getLottoCount = (price) => {
  return price.match(/\d+(?=000)/g)[0]
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
    lottos[i] = lottoTempArr
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
  const lottoCount = getLottoCount(priceInput.value)
  const myLottos = []
  setLotto(lottoCount, myLottos)
  let result = ''
  makeDescription(`${lottoCount}개를 구매했습니다.`, priceForm)
  for (let i = 0; i < lottoCount; i++) {
    result += `${myLottos[i]}<br/>`
  }
  makeDescription(`${result}`, priceForm)
  conditionForm.style.display = 'block'
})

conditionForm.addEventListener('submit', (e) => {
  e.preventDefault()
  console.log('thi')
})
