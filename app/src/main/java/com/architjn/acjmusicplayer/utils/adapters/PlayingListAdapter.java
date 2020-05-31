package com.architjn.acjmusicplayer.utils.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.architjn.acjmusicplayer.R;
import com.architjn.acjmusicplayer.service.PlayerService;
import com.architjn.acjmusicplayer.ui.widget.PointShiftingArrayList;
import com.architjn.acjmusicplayer.utils.ListSongs;
import com.architjn.acjmusicplayer.utils.Utils;
import com.architjn.acjmusicplayer.utils.items.Song;


import java.io.File;

/**
 * Created by architjn on 28/11/15.
 */
public class PlayingListAdapter extends RecyclerView.Adapter<PlayingListAdapter.MyViewHolder> {

    private Context context;
    private Activity mActivity;
    private LayoutInflater inflater;
	
	private ArrayList<Song> mSongs;
	

    public PlayingListAdapter (Context context, Activity mActivity, ArrayList<Song> data ){
        this.context = context;
        this.mActivity = mActivity;
		this.mSongs = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.songs_list_item, parent, false);
        return new MyViewHolder(view);
    }
    public void onBindViewHolder(MyViewHolder holder, final int position) {
		
		Song song = mSongs.get(position);
		holder.name.setText(song.getName());
        holder.artistName.setText(mSongs.get(position).getArtist());
        holder.mainView.setElevation(0);

        //setAlbumArt ( holder, song ) ;
		
    }

	private void setAlbumArt ( CurrentPlaylistQ.MyViewHolder holder, Song song ) {
		Uri albumArt = ListSongs.getAlbumArtUri ( song.getAlbumId() ) ;

		if ( albumArt != null ) {

			holder.img.setImageURI ( albumArt );

		} else {
			
			//holder.img.setImageResource ( R.drawable.default_art );
			
		}
		
	}

    
    public int getItemCount() {
		
        return mSongs.size();
		
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

		public TextView name, artistName;
        public View mainView, menu;
        public ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            mainView = itemView;
            img = itemView.findViewById(R.id.song_item_img);
            name = (TextView) itemView.findViewById(R.id.song_item_name);
            menu = itemView.findViewById(R.id.song_item_menu);
            artistName = (TextView) itemView.findViewById(R.id.song_item_artist);
        


        }

        
    }
	
	
}
