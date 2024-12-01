const fs = require('fs');

// Function to compare two vectors and calculate the sum of similarities
function sumOfSimilarity(vector1, vector2) {
    if (vector1.length !== vector2.length) {
      throw new Error('Vectors must be of the same size');
    }
    
    // Calculate the sum of similarities
    const sum = vector1.reduce((total, num, index) => {
      const count = vector2.filter((value) => value === num).length;
      return total + count * num;
    }, 0);
  
    return sum;
  }
  
// Export the function
module.exports = { sumOfSimilarity };