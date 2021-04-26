package trie.gui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import trie.core.Trie;

public class PredictorController {
	@FXML
	TextField prefix;
	@FXML
	ListView<String> completions;
	ObservableList<String> model;
	Trie dictionary;
	
	@FXML
	void initialize() {
		try {
			File input = new File("english3.txt");
			dictionary = new Trie();
			Scanner in = new Scanner(input);
			while (in.hasNextLine()) {
				dictionary.add(in.nextLine());
			}
			in.close();
			System.out.println(dictionary.size() + " words loaded");
			
			model = FXCollections.observableArrayList();
			completions.setItems(model);

			prefix.setOnKeyTyped(event -> {
				model.clear();
				String updated = prefix.getText();
				if (updated.length() > 0) {
					model.addAll(dictionary.successorsTo(updated));
				}
			});
		} catch (IOException ioe) {
			System.out.println(ioe.getStackTrace());
			System.exit(1);
		}
	}
	
	
}
