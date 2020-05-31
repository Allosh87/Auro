package com.architjn.acjmusicplayer.ui.layouts.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageButton;
import android.content.DialogInterface;
import com.architjn.acjmusicplayer.R;
import com.architjn.acjmusicplayer.utils.adapters.PlayingListAdapter;
import com.architjn.acjmusicplayer.ui.layouts.activity.MainActivity;
import com.architjn.acjmusicplayer.ui.widget.PointShiftingArrayList;
import com.architjn.acjmusicplayer.utils.items.Song;
import java.util.ArrayList;

import com.allosh.xtraplayer.utils.ListSongs;
import android.support.v7.app.AppCompatActivity;

import com.allosh.xtraplayer.utils.PermissionChecker;
import com.allosh.xtraplayer.utils.adapters.CurrentPlaylistQ;
import android.app.Activity;

public class UpNextFragment extends BottomSheetDialogFragment {

	private View mView;
	private Context mContext;
	private RecyclerView mRecyclerView;

	private PlayingListAdapter mAdapter;
	private Activity mActivity;
	private CurrentPlaylistQ adapter;


	@SuppressLint("RestrictedApi")
	@Override
	public void setupDialog ( Dialog dialog, int style ) {
		super.setupDialog ( dialog, style );
		mView = getActivity ( ).getLayoutInflater ( ).inflate ( R.layout.layout_bottomsheet_queue, null, false );
		mContext = getActivity ( ).getApplicationContext ( );

		setHasOptionsMenu ( true );
		
		mRecyclerView = mView.findViewById(R.id.recycler_player);
		
     
		getDialog ( ).setOnShowListener ( new DialogInterface.OnShowListener ( ) {

			@Override
			public void onShow ( DialogInterface dialog1 ) {

				BottomSheetDialog d = (BottomSheetDialog) dialog1;
				View bottomSheetInternal = d.findViewById ( android.support.design.R.id.design_bottom_sheet );
				BottomSheetBehavior.from ( bottomSheetInternal ).setState ( BottomSheetBehavior.STATE_COLLAPSED );

			}

		} );

		setUpRecycler();

		dialog.setContentView ( mView );

		( (View) mView.getParent ( ) ).setBackgroundColor ( getResources ( ).getColor ( android.R.color.transparent ) );

	}

	private void setUpRecycler ( ) {
		
		ArrayList<Song> mSongs = ListSongs.getSongList( mContext );
        adapter = new CurrentPlaylistQ ( mContext, mActivity, mSongs );
        mRecyclerView.setAdapter ( adapter );
        mRecyclerView.setLayoutManager ( new LinearLayoutManager ( mContext ) );
        
		
	}
		
	

	public void removeFragment ( ) {
		getActivity ( ).getSupportFragmentManager ( ).beginTransaction ( ).remove ( this ).commit ( );
	}

	public PlayingListAdapter getAdapter() {
        return mAdapter;
    }
	

	@Override
	public void onDestroy ( ) {
		super.onDestroy ( );
	}

	@Override
	public void onResume ( ) {
		// TODO: Implement this method
		super.onResume ( );
		setUpRecycler ();
	}

	


}
