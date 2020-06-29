package com.eme.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;

import com.eme.tictactoe.databinding.ActivityMainBinding;
import com.eme.tictactoe.model.Player;
import com.eme.tictactoe.presenter.ITicTacToePresenter;
import com.eme.tictactoe.presenter.ITicTacToeView;
import com.eme.tictactoe.presenter.TicTacToePresenter;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements ITicTacToeView {

    private TicTacToePresenter presenter;

    private ActivityMainBinding binding;

    private ImageButton currentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new TicTacToePresenter(this);
        setupLog();
    }

    private void setupLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public void setCellImage(int imageResource) {
        Timber.d("setCellImage() called with: imageResource = [" + imageResource + "]");
        currentView.setImageResource(imageResource);
    }

    @Override
    public void showWinner(Player winner) {
        Timber.d("showWinner() called with: winner = [" + winner + "]");
        binding.tvDialogTitle.setText(R.string.dialog_title_winner);
        binding.tvDialogTitle.setVisibility(View.VISIBLE);

        binding.tvDialogMessage.setText(winner != null ? winner.getName() : getResources().getString(R.string.dialog_winner));
        binding.tvDialogMessage.setVisibility(View.VISIBLE);

        binding.ivDialogResult.setImageResource(R.drawable.ic_dialog_winner);
        binding.ivDialogResult.setVisibility(View.VISIBLE);

        binding.ivPlayerWinner.setImageResource(winner.getAvatar());
        binding.ivPlayerWinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDraw() {
        Timber.d("showDraw() called");
        binding.tvDialogTitle.setText(R.string.dialog_title_draw);
        binding.tvDialogTitle.setVisibility(View.VISIBLE);
        binding.tvDialogMessage.setVisibility(View.INVISIBLE);
        binding.ivDialogResult.setImageResource(R.drawable.ic_dialog_draw);
        binding.ivDialogResult.setVisibility(View.VISIBLE);
        binding.ivPlayerWinner.setVisibility(View.INVISIBLE);
    }

    @Override
    public void clearScreen() {
        Timber.d("clearScreen() called");
        this.currentView = null;

        for(int i=0; i<binding.mainGridLayout.getChildCount(); i++) {
            ((ImageButton) binding.mainGridLayout.getChildAt(i)).setImageResource(R.drawable.background_default_cell);
        }

        binding.tvDialogTitle.setVisibility(View.INVISIBLE);
        binding.tvDialogMessage.setVisibility(View.INVISIBLE);
        binding.ivDialogResult.setVisibility(View.INVISIBLE);
        binding.ivPlayerWinner.setVisibility(View.INVISIBLE);
    }

    public void move(View view) {
        Timber.d("move() called with: view = [" + view + "]");
        if(presenter.isGameInProgress()) {
            currentView = (ImageButton) view;

            String resourceEntryName = getResources().getResourceEntryName(view.getId());

            Pair<Integer, Integer> location = getLocation(resourceEntryName);

            presenter.setCell(location.first, location.second);
        }
    }

    public void onRestartGame(View view) {
        this.presenter.restartGame();
    }

    public static Pair<Integer, Integer> getLocation(final String id) {
        final String[] cells = id.replace("cell_", "").split("_");

        return new Pair<>(Integer.valueOf(cells[0]), Integer.valueOf(cells[1]));
    }
}