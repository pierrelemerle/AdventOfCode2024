// Import functions from part1.js
const { readNumbersIntoVectors, sumOfDifferences } = require('./part1.js');
const { sumOfSimilarity } = require('./part2.js');

const filePath = './inputday1.txt'; 
const { vector1, vector2 } = readNumbersIntoVectors(filePath);

//part1
try {
    const differenceSum = sumOfDifferences(vector1, vector2);
    console.log('Sum of differences:', differenceSum);
  } catch (err) {
    console.error(err.message);
  }

//part2
try {
    const similarity = sumOfSimilarity(vector1, vector2);
    console.log('Sum of similarity:', similarity);
  } catch (err) {
    console.error(err.message);
  }