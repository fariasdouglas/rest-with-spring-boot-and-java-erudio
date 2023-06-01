package br.com.farias;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.farias.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {

	private static final String template = "Hello, %s!";
	private static final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",method=RequestMethod.GET)
	public Double sum(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) { 
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return convertToDouble(numberOne) + convertToDouble(numberTwo) ;
	}

	@RequestMapping(value = "/subtract/{numberOne}/{numberTwo}",method=RequestMethod.GET)
	public Double subtract(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) { 
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return convertToDouble(numberOne) - convertToDouble(numberTwo) ;
	}
	
	@RequestMapping(value = "/multiply/{numberOne}/{numberTwo}",method=RequestMethod.GET)
	public Double multiply(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) { 
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return convertToDouble(numberOne) * convertToDouble(numberTwo) ;
	}
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}",method=RequestMethod.GET)
	public Double div(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) { 
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return convertToDouble(numberOne) / convertToDouble(numberTwo) ;
	}
	
	private Double convertToDouble(String strNumber) {
		if (strNumber == null) return 0D;
		// BR 10,25 US 10.25
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
}
