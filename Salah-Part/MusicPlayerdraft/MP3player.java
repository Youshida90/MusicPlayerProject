package application;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import Project.Playlist;
import Tree.BinarySearchTree;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MP3player extends Application {
	MediaPlayer mediaPlayer; // Declare the MediaPlayer instance here
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BinarySearchTree bst = new BinarySearchTree();
		BinarySearchTree bst1 = new BinarySearchTree();
		Playlist playlist = new Playlist();
		// Create a Button object for the play button
		Button playButton = new Button("Play");
		playButton.setFocusTraversable(false);
		// Create a Button object for the pause button
		Button pauseButton = new Button("Pause");
		pauseButton.setFocusTraversable(false);
		// Create a Button object for the restart button
		Button restartButton = new Button("Restart");
		restartButton.setFocusTraversable(false);
		// Create a Slider object for the minutes
		Slider slider = new Slider(0, 1, 0);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(0.1);
		slider.setMinorTickCount(0);
		slider.setFocusTraversable(false);
		// Create a Button object for the add button
		Button addButton = new Button("Add to Playlist");
		Text t1 = new Text("Your PlayList");
		addButton.setFocusTraversable(false);
		TextField tf = new TextField();
		tf.setFocusTraversable(false);
		Text songfound = new Text();
		Text songnotfound = new Text();
		// Add an event listener to the TextField
		tf.textProperty().addListener((observable, oldValue, newValue) -> {
			File result = bst.search(newValue);
			if (result != null) {
				// Display the result
				// This depends on how you want to display the result
				// To Clear the songnotfound message if the song is found
				songnotfound.setText("");
		        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		        alert.setTitle("Confirmation");
		        alert.setHeaderText(null);
		        alert.setContentText("Song found: " + result.getName());
		        alert.showAndWait();
			} else {
				songnotfound.setText("");
				songfound.setText("");
				songnotfound.setText("No song found for the given title.");
			}
		});
		Text t = new Text("Your added music");
//-----------------------------------------------------------------------------------------------------------------------------------------------------		
		// Create a HashMap to store the file paths
		HashMap<String, String> filePaths = new HashMap<>();
		Button delete = new Button("Delete");
		delete.setFocusTraversable(false);
		ObservableList<String> item = FXCollections.observableArrayList();
		ListView<String> l = new ListView<>(item);
		l.setFocusTraversable(false);
		l.setOnMouseClicked(eve -> {
			String selectedSongName = l.getSelectionModel().getSelectedItem();
			String selectedSongPath = filePaths.get(selectedSongName);
			if (selectedSongPath != null) {
				File file = new File(selectedSongPath);
				if (file.exists()) {
					Media media = new Media(file.toURI().toString());
					if (mediaPlayer != null) {
			            mediaPlayer.stop(); // Stop the current song if there is one
			        }
					 mediaPlayer = new MediaPlayer(media); // Assign the new MediaPlayer instance to the existing field
					playButton.setOnAction(e -> {
					    if (l.getItems().isEmpty()) {
					        if (mediaPlayer != null) {
					            mediaPlayer.stop();
					        }
					        return;
					    }
					    if (mediaPlayer != null) {
					        mediaPlayer.play();
					    }else {
					        Alert alert = new Alert(Alert.AlertType.WARNING);
					        alert.setTitle("Warning");
					        alert.setHeaderText(null);
					        alert.setContentText("Add a song");
					        alert.showAndWait();
					    }
					});
					pauseButton.setOnAction(e -> {
						mediaPlayer.pause();
						if(mediaPlayer == null) {
					        Alert alert = new Alert(Alert.AlertType.WARNING);
					        alert.setTitle("Warning");
					        alert.setHeaderText(null);
					        alert.setContentText("Add a song");
					        alert.showAndWait();
						}
						});
					restartButton.setOnAction(e -> {
						if (l.getItems().isEmpty()) {
					        if (mediaPlayer != null) {
					            mediaPlayer.stop();
					        }else {
					        	Alert alert = new Alert(Alert.AlertType.WARNING);
						        alert.setTitle("Warning");
						        alert.setHeaderText(null);
						        alert.setContentText("Add a song");
						        alert.showAndWait();
					        }
					        return;
					    }
					    if (mediaPlayer != null) {
					    	mediaPlayer.stop();
					        mediaPlayer.seek(Duration.ZERO);
					        mediaPlayer.play();
					    }
					});
					// Add a listener to the MediaPlayer's currentTimeProperty to update the slider
					mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
					    if (!slider.isValueChanging()) { // This check prevents the slider's value from being updated while the user is dragging it
					        slider.setValue(newValue.toSeconds() / mediaPlayer.getTotalDuration().toSeconds());
					    }
					});

					// Add an event handler to the slider's valueProperty to seek the media player
					slider.valueChangingProperty().addListener((observable, oldValue, isChanging) -> {
					    if (!isChanging) {
					        mediaPlayer.seek(Duration.seconds(slider.getValue() * mediaPlayer.getTotalDuration().toSeconds()));
					    }
					});
					delete.setOnAction(q -> {
						String selectedSongName1 = l.getSelectionModel().getSelectedItem();
						if (selectedSongName != null) {
							String selectedSongPath1 = filePaths.get(selectedSongName1);
							if (selectedSongPath != null) {
								File selectedSongFile = new File(selectedSongPath1);
								playlist.deleteSong(selectedSongFile);
								bst1.delete(selectedSongName1); // delete the song from the BinarySearchTree
								item.remove(selectedSongName1);
							}
						}else {
					        Alert alert = new Alert(Alert.AlertType.WARNING);
					        alert.setTitle("Warning");
					        alert.setHeaderText(null);
					        alert.setContentText("Add a song because there is no songs to delete");
					        alert.showAndWait();
						}
					});
					songnotfound.setText("");
				} else {
					songnotfound.setText("File does not exist: " + selectedSongPath);
				}
			} else {
		        Alert alert = new Alert(Alert.AlertType.WARNING);
		        alert.setTitle("Warning");
		        alert.setHeaderText(null);
		        alert.setContentText("No song selected.");
		        alert.showAndWait();
			}
		});
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		ObservableList<String> items = FXCollections.observableArrayList();
		ListView<String> listView = new ListView<>(items);
		listView.setFocusTraversable(false);
		listView.setOnMouseClicked(ev -> {
			String selectedSongName = listView.getSelectionModel().getSelectedItem();
			String selectedSongPath = filePaths.get(selectedSongName);
			if (selectedSongPath != null) {
				File file = new File(selectedSongPath);
				if (file.exists()) {
					Media media = new Media(file.toURI().toString());
			        if (mediaPlayer != null) {
			            mediaPlayer.stop(); // Stop the current song if there is one
			        }
			        mediaPlayer = new MediaPlayer(media); // Assign the new MediaPlayer instance to the existing field
			        playButton.setOnAction(e -> {
			            // Get the selected song
			            String selectedSongName1 = listView.getSelectionModel().getSelectedItem();

			            // Check if the song is in the filePaths HashMap
			            if (!filePaths.containsKey(selectedSongName1)) {
			                System.err.println("The song has been deleted.");
			                // Stop playing the song
			                mediaPlayer.stop();
			                return;
			            }
					    if (mediaPlayer != null) {
					        mediaPlayer.play();
					    }else {
					        Alert alert = new Alert(Alert.AlertType.WARNING);
					        alert.setTitle("Warning");
					        alert.setHeaderText(null);
					        alert.setContentText("Add a song");
					        alert.showAndWait();
					    }
					});
					pauseButton.setOnAction(e -> {
						mediaPlayer.pause();
						if(mediaPlayer == null) {
					        Alert alert = new Alert(Alert.AlertType.WARNING);
					        alert.setTitle("Warning");
					        alert.setHeaderText(null);
					        alert.setContentText("Add a song");
					        alert.showAndWait();
						}
						});
					restartButton.setOnAction(e -> {
						if (listView.getItems().isEmpty() && l.getItems().isEmpty()) {
					        if (mediaPlayer != null) {
					            mediaPlayer.stop();
					        }
					        return;
					    }
					    if (mediaPlayer != null) {
					        mediaPlayer.seek(Duration.ZERO);
					        mediaPlayer.play();
					        
					    }else {
					        Alert alert = new Alert(Alert.AlertType.WARNING);
					        alert.setTitle("Warning");
					        alert.setHeaderText(null);
					        alert.setContentText("Add a song");
					        alert.showAndWait();
					    }
					});
					// Add a listener to the MediaPlayer's currentTimeProperty to update the slider
					mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
					    if (!slider.isValueChanging()) { // This check prevents the slider's value from being updated while the user is dragging it
					        slider.setValue(newValue.toSeconds() / mediaPlayer.getTotalDuration().toSeconds());
					    }
					});

					// Add an event handler to the slider's valueProperty to seek the media player
					slider.valueChangingProperty().addListener((observable, oldValue, isChanging) -> {
					    if (!isChanging) {
					        mediaPlayer.seek(Duration.seconds(slider.getValue() * mediaPlayer.getTotalDuration().toSeconds()));
					    }
					});
					delete.setOnAction(q -> {

						if (selectedSongName != null) {

							if (selectedSongPath != null) {
								File selectedSongFile = new File(selectedSongPath);
								playlist.deleteSong(selectedSongFile); // delete the song from the playlist
								bst.delete(selectedSongName); // delete the song from the BinarySearchTree
								filePaths.remove(selectedSongName); // delete the song from the added songs list
								items.remove(selectedSongName); // delete the song from the ListView
								item.remove(selectedSongName); // delete the song from the ListView
							}
						}else {
					        Alert alert = new Alert(Alert.AlertType.WARNING);
					        alert.setTitle("Warning");
					        alert.setHeaderText(null);
					        alert.setContentText("Add a song because there is no songs to delete");
					        alert.showAndWait();
						}
					});
					songnotfound.setText("");
				} else {
					songnotfound.setText("File does not exist: " + selectedSongPath);
				}
			} else {
		        Alert alert = new Alert(Alert.AlertType.WARNING);
		        alert.setTitle("Warning");
		        alert.setHeaderText(null);
		        alert.setContentText("No song selected.");
		        alert.showAndWait();
			}
		});
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		addButton.setOnAction(c -> {
			String selectedSongName = listView.getSelectionModel().getSelectedItem();
			if (selectedSongName != null) {
				String selectedSongPath = filePaths.get(selectedSongName);
				if (selectedSongPath != null) {
					File selectedSongFile = new File(selectedSongPath);
					playlist.addSong(selectedSongFile); // Add the song to the playlist
					item.add(selectedSongName); // Add the song name to the ListView
				} else {
			        Alert alert = new Alert(Alert.AlertType.WARNING);
			        alert.setTitle("Warning");
			        alert.setHeaderText(null);
			        alert.setContentText("No song path found for the selected song.");
			        alert.showAndWait();
				}
			} else {
		        Alert alert = new Alert(Alert.AlertType.WARNING);
		        alert.setTitle("Warning");
		        alert.setHeaderText(null);
		        alert.setContentText("No song selected.");
		        alert.showAndWait();
			}
		});

		Button add = new Button("Add a music from your computer");
		add.setFocusTraversable(false);
		add.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));
			File selectedFile = fileChooser.showOpenDialog(primaryStage);

			if (selectedFile != null) {
				String fileName = selectedFile.getName();
				String fullPath = selectedFile.getPath();
				filePaths.put(fileName, fullPath);
				items.add(fileName);
				bst.insert(fileName, selectedFile);
				songnotfound.setText("");
			} else {
		        Alert alert = new Alert(Alert.AlertType.WARNING);
		        alert.setTitle("Warning");
		        alert.setHeaderText(null);
		        alert.setContentText("File selection cancelled.");
		        alert.showAndWait();
			}
		});
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		Button shuffle = new Button("shuffle");
		shuffle.setFocusTraversable(false);
		shuffle.setOnAction(e -> {
		    if (item.isEmpty()) {
		        Alert alert = new Alert(Alert.AlertType.WARNING);
		        alert.setTitle("Warning");
		        alert.setHeaderText(null);
		        alert.setContentText("Please add a song to the Playlist before shuffling.");
		        alert.showAndWait();
		    } else {
		        playlist.shuffle(); // Shuffle the songs in the playlist
		        List<String> playOrderSongs = playlist.getPlayOrderSongNames(); // Get the song names in the play order
		        item.clear(); // Clear the ListView
		        item.addAll(playOrderSongs); // Add the song names in the play order to the ListView
		    }
		});
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------				

		// Create an HBox object to hold the buttons and slider
		HBox hbox = new HBox(10, playButton, pauseButton, restartButton, slider, addButton, add, shuffle, delete);
		VBox v = new VBox(10, tf, listView, t);
		VBox v1 = new VBox(10, t1, l);
		VBox v2 = new VBox(10, songfound, songnotfound);
		v.setPadding(new Insets(10));
		v1.setPadding(new Insets(10));
		hbox.setAlignment(Pos.CENTER);
		// Create a BorderPane object to hold the MediaView and HBox
		BorderPane borderPane = new BorderPane();
		borderPane.setLeft(v);
		borderPane.setCenter(v2);
		borderPane.setRight(v1);
		borderPane.setBottom(hbox);

		// Create a Scene object with the BorderPane
		Scene scene = new Scene(borderPane, 640, 480);
		// Set the Scene to the Stage and show the Stage
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Mp3 Player");
	}
}