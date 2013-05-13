package fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.pixelart.MainActivity;
import com.pixelart.R;
import com.pixelart.Tools;

public class ShareFragment extends Fragment {

	int mCurrentPage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle data = this.getArguments();
		this.mCurrentPage = data.getInt("current_page", 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.fragment_share, container,false);
		Button save = (Button) rl.findViewById(R.id.save);

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					Bitmap merged = Tools.savedBitmap;
					File imagesFolder = new File(Environment.getExternalStorageDirectory(), "folder");
					if(!imagesFolder.exists())
						imagesFolder.mkdirs();
					int imageNum = imagesFolder.list() == null ? 1 : imagesFolder.list().length + 1;
					String fileName = "file_" + String.valueOf(imageNum) + ".jpg";
					File output = new File(imagesFolder, fileName);
					while(output.exists()){
						imageNum++;
						fileName = "file_" + String.valueOf(imageNum) + ".jpg";
						output = new File(imagesFolder, fileName);
					}
					OutputStream fOut = new FileOutputStream(output);
					merged.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
					fOut.flush();
					fOut.close();

					Toast.makeText( ShareFragment.this.getActivity(), "Saved", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent();
					intent.setType("image/*");
					intent.setAction(Intent.ACTION_GET_CONTENT);//
					startActivityForResult(Intent.createChooser(intent, "Select Picture"),imageNum);
				} catch (Exception e) {
					Toast.makeText(ShareFragment.this.getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
				}
			}
		});

		Button menu = (Button) rl.findViewById(R.id.menu);
		menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ShareFragment.this.getActivity(), MainActivity.class);
				ShareFragment.this.startActivity(i);
			}
		});
		return rl;
	}
}
