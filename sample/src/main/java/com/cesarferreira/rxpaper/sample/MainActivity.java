package com.cesarferreira.rxpaper.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cesarferreira.rxpaper.RxPaper;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

	private TextView targetTextView;

	private Person defaultValue = null;
	private final String PERSON_KEY = "PERSON_KEY";
	private final String ANOTHER_PERSON_KEY = "ANOTHER_PERSON_KEY";

	private String mCustomBook = "SOME_BOOK";

	Person person = new Person("Cesar", "Ferreira");
	Person anotherPerson = new Person("Ivo", "Ferreira");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		targetTextView = (TextView) findViewById(R.id.targetTextView);

	}

	private void log(String s) {
		Log.d("rxpaper", s);
		targetTextView.setText(s);
	}

	public void writeCustom(View view) {
		RxPaper.book(mCustomBook)
			.write(PERSON_KEY, anotherPerson)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<Person>() {
				@Override
				public void onCompleted() {

				}

				@Override
				public void onError(Throwable e) {
					log(e.getMessage());
				}

				@Override
				public void onNext(Person person) {
					log("Custom write success!!! " + person.getFirstName());

				}
			});
	}

	public void write(View view) {
		RxPaper.book()
			.write(PERSON_KEY, person)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<Person>() {
				@Override
				public void onCompleted() {

				}

				@Override
				public void onError(Throwable e) {
					log(e.getMessage());
				}

				@Override
				public void onNext(Person person) {
					if (person != null) {
						log("Write success!!!");
					}
				}

			});
	}

	public void readCustom(View view) {
		RxPaper.book(mCustomBook)
			.read(PERSON_KEY, defaultValue)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<Person>() {
				@Override
				public void onCompleted() {

				}

				@Override
				public void onError(Throwable e) {
					log(e.getMessage());

				}

				@Override
				public void onNext(Person person) {
					log(String.valueOf(person));
				}
			});
	}

	public void read(View view) {
		RxPaper.book()
			.read(PERSON_KEY, defaultValue)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<Person>() {
				@Override
				public void onCompleted() {

				}

				@Override
				public void onError(Throwable e) {
					log(e.getMessage());
				}

				@Override
				public void onNext(Person person) {
					log(String.valueOf(person));
				}
			});
	}

	public void destroy(View view) {
		RxPaper.book()
			.destroy()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<Boolean>() {
				@Override
				public void onCompleted() {

				}

				@Override
				public void onError(Throwable e) {
					log(e.getMessage());
				}

				@Override
				public void onNext(Boolean success) {
					log("Data destroyed");
				}
			});

	}

	public void destroyCustom(View view) {
		RxPaper.book(mCustomBook)
			.destroy()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<Boolean>() {
				@Override
				public void onCompleted() {

				}

				@Override
				public void onError(Throwable e) {
					log(e.getMessage());
				}

				@Override
				public void onNext(Boolean success) {
					log("Custom data destroyed");
				}
			});
	}

	public void getAllKeys(View view) {
		RxPaper.book()
			.getAllKeys()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<List<String>>() {
				@Override
				public void onCompleted() {

				}

				@Override
				public void onError(Throwable e) {
					log(e.getMessage());
				}

				@Override
				public void onNext(List<String> allKeys) {
					StringBuilder output = new StringBuilder();

					for (String s : allKeys) {
						output.append(s).append("\n");
					}

					log(String.valueOf(output));
				}
			});
	}
}
