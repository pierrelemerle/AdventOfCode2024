const fs = require('fs');

// Function to read numbers from a file and split them into two vectors
function readNumbersIntoVectors(filePath) {
  try {
    // Read file content
    const data = fs.readFileSync(filePath, 'utf8');
    
    // Split data into an array of numbers (split by space or newline)
    const numbers = data
      .split(/\s+/) // Split by any whitespace
      .filter(Boolean) // Remove empty strings
      .map(Number); // Convert strings to numbers
    
    // Create two vectors
    const vector1 = [];
    const vector2 = [];
    
    // Distribute numbers between the two vectors
    numbers.forEach((num, index) => {
      if (index % 2 === 0) {
        vector1.push(num); // Even index -> vector1
      } else {
        vector2.push(num); // Odd index -> vector2
      }
    });

    // Sort the vectors in ascending order
    vector1.sort((a, b) => a - b);
    vector2.sort((a, b) => a - b);

    return { vector1, vector2 };
  } catch (err) {
    console.error('Error reading file:', err);
    return { vector1: [], vector2: [] };
  }
}


// Function to compare two vectors and calculate the sum of differences
function sumOfDifferences(vector1, vector2) {
    if (vector1.length !== vector2.length) {
      throw new Error('Vectors must be of the same size');
    }
  
    // Calculate the sum of differences
    const sum = vector1.reduce((total, num1, index) => {
      const difference = Math.abs(num1 - vector2[index]); // Calculate absolute difference
      return total + difference;
    }, 0);
  
    return sum;
  }
  

// Example usage
const filePath = './inputday1.txt'; // Replace with your file path
const { vector1, vector2 } = readNumbersIntoVectors(filePath);
console.log('Vector 1:', vector1);
console.log('Vector 2:', vector2);

try {
    const differenceSum = sumOfDifferences(vector1, vector2);
    console.log('Sum of differences:', differenceSum);
  } catch (err) {
    console.error(err.message);
  }