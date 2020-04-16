const makeDescription = (string, parrentElem) => {
  const description = document.createElement('div')
  description.innerHTML = `${string}`
  parrentElem.append(description)
}

const getLottoCount = (price) => {
  return price % LOTTO_PRICE === 0 ? price / LOTTO_PRICE : 0
}

const setLotto = (lottoCount, lottos) => {
  for (let i = 0; i < lottoCount; i++) {
    lottos[i] = new Lotto(setRandomNumbers().sort((a, b) => a - b))
  }
}

const setRandomNumbers = () => {
  let takenLottoNumbers = [...lottoNumbers]
  resultNumbers = []
  for (let j = 0; j < LOTTO_LENGTH; j++) {
    const randomNumber = Math.floor(Math.random() * (LOTTO_MAX_NUMBER - j))
    const chosen = takenLottoNumbers.splice(randomNumber, 1)[0]
    resultNumbers.push(Number(chosen))
  }
  return resultNumbers
}

const matchLottos = (myLottos, winningLotto) => {
  for (let i = 0; i < myLottos.length; i++) {
    rank[winningLotto.match(myLottos[i])]++
  }
}

const checkInputValue = (lottos, bonusBall) => {
  let overlappingNumbers = new Set()
  lottos = lottos.split(',')
  if (lottos.length !== LOTTO_LENGTH) return false
  if (bonusBall > LOTTO_MAX_NUMBER || bonusBall <= 0) return false
  lottos.map((item) => {
    if (item > LOTTO_MAX_NUMBER || item <= 0) return false
  })
  for (let i = 0; i < lottos.length; i++) {
    overlappingNumbers.add(lottos[i])
  }
  if (overlappingNumbers.size !== LOTTO_LENGTH) return false
  if (lottos.indexOf(bonusBall) >= 0) return false
  return true
}
