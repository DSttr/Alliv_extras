package in.ds.alliv.control;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import in.ds.alliv.control.utils.*;
//import com.android.internal.telephony.cat.*;

public class DSAdapter
{
	public interface DView {

        void onBindViewHolder(RecyclerView.ViewHolder viewHolder);

        RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup);

        String getTitle();

    }
	
	public static class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
	{

		public interface OnItemClickListener {
            void onItemClick(View view, int position);
        }

        public final List<DView> DViews;
        private OnItemClickListener onItemClickListener;
        private boolean itemOnly;

        public Adapter(List<DView> DViews) {
            this.DViews = DViews;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            DViews.get(position).onBindViewHolder(holder);
        }

        @Override
        public int getItemCount() {
            return DViews.size();
        }

        public void setItemOnly(boolean itemOnly) {
            this.itemOnly = itemOnly;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = DViews.get(viewType).onCreateViewHolder(parent);
            setOnClickListener(DViews.get(viewType), viewHolder.itemView);
            return viewHolder;
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        private void setOnClickListener(final DView dView, View view) {
            boolean onClick = false;
            //if (itemOnly)
                //onClick = dView instanceof Item;
          //  else if (onItemClickListener != null) onClick = true;
            if (onClick) {
                view.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							if (onItemClickListener != null)
								onItemClickListener.onItemClick(v, DViews.indexOf(dView));
						}
					});
            }
        }
	}
	
    public static class MainHeader implements DView {

        private static ImageView image;
        private boolean noPic;

        @Override
        public String getTitle() {
            return null;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.navigation_header, viewGroup, false);
            image = (ImageView) view.findViewById(R.id.header_pic);
            try {
                String uri = Utils.getString("previewpicture", null, image.getContext());
                if (uri == null || uri.equals("nopicture")) noPic = true;
                else {
                    setImage(Uri.parse(uri));
                    noPic = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                noPic = true;
            }

            if (noPic) Utils.saveString("previewpicture", "nopicture", image.getContext());
            view.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(final View v) {
						new AlertDialog.Builder(v.getContext()).setItems(v.getResources()
                            .getStringArray(R.array.main_header_picture_items), new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									switch (which) {
										case 0:
											v.getContext().startActivity(new Intent(v.getContext(), MainHeaderActivity.class));
											break;
										case 1:
											if (Utils.getString("previewpicture", null, v.getContext()).equals("nopicture"))
												return;
											Utils.saveString("previewpicture", "nopicture", v.getContext());
											image.setImageDrawable(null);
											animate();
											break;
									}

								}
							}).show();
					}
				});

            if (Utils.isTV(view.getContext())) {
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
            }
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        }

        public static class MainHeaderActivity extends Activity {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                Intent intent;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                } else {
                    intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                }
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, getString(R.string.select_picture)), 0);
            }

            @Override
            protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == RESULT_OK && requestCode == 0)
                    try {
                        Uri selectedImageUri = data.getData();
                        setImage(selectedImageUri);
                        Utils.saveString("previewpicture", selectedImageUri.toString(), this);
                        animate();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Utils.toast(getString(R.string.went_wrong), MainHeaderActivity.this);
                    }
                finish();
            }

        }

        public static void animate() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                image.setVisibility(View.INVISIBLE);
                new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(500);
								((Activity) image.getContext()).runOnUiThread(new Runnable() {
										@Override
										public void run() {
											Utils.circleAnimate(image, image.getWidth() / 2, image.getHeight() / 2);
										}
									});
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}).start();
            }
        }

        public static void setImage(Uri uri) throws IOException, NullPointerException {
            String selectedImagePath = null;
            try {
                selectedImagePath = getPath(uri, image.getContext());
            } catch (Exception ignored) {
            }
            Bitmap bitmap;
            if ((bitmap = selectedImagePath != null ? BitmapFactory.decodeFile(selectedImagePath) :
				uriToBitmap(uri, image.getContext())) != null)
                image.setImageBitmap(Utils.scaleDownBitmap(bitmap, 1024, 1024));
            else throw new NullPointerException("Getting Bitmap failed");
        }

        private static Bitmap uriToBitmap(Uri uri, Context context) throws IOException {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                context.getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return bitmap;
        }

        private static String getPath(Uri uri, Context context) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA},
															   null, null, null);
            if (cursor != null) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String path = cursor.getString(column_index);
                cursor.close();
                return path;
            } else return null;
        }

    }
}
