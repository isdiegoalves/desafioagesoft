package br.diego.tokenwebcrawler;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Matematico {

	private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

	public Number resolverProblema(String expressaoMatematica) {
		try {
			return (Number) engine.eval(expressaoMatematica);
		} catch (ScriptException e) {
			throw new UnsupportedOperationException("O Matematico nao conseguiu resolver a expressao "+ expressaoMatematica, e);
		}
	}
}
