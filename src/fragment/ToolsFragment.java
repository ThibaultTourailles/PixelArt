package fragment;

import view.MyToolsView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pixelart.ShakeDetector;

public class ToolsFragment extends Fragment {

	int mCurrentPage;
	ShakeDetector detector;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle data = this.getArguments();
		this.mCurrentPage = data.getInt("current_page", 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final MyToolsView v = new MyToolsView(this.getActivity().getApplicationContext());
		return v;
	}
}
