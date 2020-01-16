package br.com.andrebaroni.raspberryawards.infra.inicialization;

public class LoadMovieDataException extends RuntimeException {

    public LoadMovieDataException() {
        super("Failed to load movie data");
    }
}
