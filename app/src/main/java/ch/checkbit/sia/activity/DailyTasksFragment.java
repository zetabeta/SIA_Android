package ch.checkbit.sia.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

import ch.checkbit.sia.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DailyTasksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DailyTasksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyTasksFragment extends Fragment {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy");
    private static final String ARG_POSITION = "position";

    private int position = 0;


    private OnFragmentInteractionListener mListener;

    public DailyTasksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DailyTasksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DailyTasksFragment newInstance(String param1) {
        DailyTasksFragment fragment = new DailyTasksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_POSITION, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }

        View view = inflater.inflate(R.layout.fragment_daily_tasks, container, false);
        TextView dateView = (TextView) view.findViewById(R.id.selectedDate);

        DateTime dateTime = new DateTime(new Date());
        if(position != 0) {
            dateTime = dateTime.plusDays(position);
        }
        dateView.setText(DATE_FORMAT.format(dateTime.toLocalDate().toDate()));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
