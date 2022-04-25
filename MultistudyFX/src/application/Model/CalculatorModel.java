package application.Model;

public class CalculatorModel {

	/*
	 * This class performs binary operations, which will include 2 numbers and they
	 * will operate a specific function based on the operator text
	 */
	public float calculateBinary(float num1, float num2, String operator) {

		switch (operator) {
		case "+":
			return num1 + num2;
		case "-":
			return num1 - num2;
		case "*":
			return num1 * num2;
		case "/":
			if (num2 == 0)
				return 0;

			return num1 / num2;
		case "mod":
			return num1 % num2;
		case "x^y":
			return (float) Math.pow(num1, num2);
		default:
			break;
		}

		return 0;
	}

	/*
	 * This class performs unary operations, which will include only 1 number. This
	 * will also operate a specific function based on the operator text
	 */
	public float calculateUnary(float num1, String operator) {

		switch (operator) {
		case "sqrt":
			return (float) Math.sqrt(num1);
		case "Sin":
			return (float) Math.sin(num1);
		case "Cos":
			return (float) Math.cos(num1);
		case "Tan":
			return (float) Math.tan(num1);
		case "Log":
			return (float) Math.log10(num1);
		case "ln":
			return (float) Math.log(num1);
		case "e^x":
			return (float) Math.exp(num1);
		case "x^2":
			return num1 * num1;
		case "x^3":
			return num1 * num1 * num1;
		case "x!":
			int fact = 1;
			for (int i = 1; i <= num1; ++i)
				fact = fact * i;
			return fact;
		default:
			break;
		}

		return 0;
	}
}
