package fragment;

import com.pixelart.ShakeDetector;
import com.pixelart.ShakeDetector.OnShakeListener;

import view.MyTouchView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DrawingFragment extends Fragment {

	int mCurrentPage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle data = this.getArguments();
		this.mCurrentPage = data.getInt("current_page", 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final MyTouchView v = new MyTouchView(this.getActivity().getApplicationContext());
		@SuppressWarnings("unused")
		ShakeDetector detector = new ShakeDetector(new OnShakeListener() {

			@Override
			public void onShake() {
				AlertDialog.Builder builder = new AlertDialog.Builder(DrawingFragment.this.getActivity());
				builder.setTitle("Clean")
				.setMessage("Are you sure you want to clean the screen ?")
				.setCancelable(false)
				.setPositiveButton("Of Course", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						v.clean();
					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
				builder.create().show();
			}
		});
		return v;
	}

}