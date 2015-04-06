package com.polymtl.shoppingsolver.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.polymtl.shoppingsolver.MainActivity;
import com.polymtl.shoppingsolver.PaymentFragment;
import com.polymtl.shoppingsolver.R;
import com.polymtl.shoppingsolver.model.Product;
import com.polymtl.shoppingsolver.model.ShoppingItem;

/**
 * Created by Zoe on 15-04-03.
 */
public class ItemDialogFragment extends DialogFragment {

    private int position; //the selected item position
    private View rootView;


    private TextView tvItemName, tvUnitPrice, tvTotalPrice, tvRatioTVQ, tvRatioTPS;
    private EditText edQuantity;

    private Spinner spinnerUnit;
    private ImageButton btnDecrease, btnIncrease, btnDelete, btnSave, btnCancel;

    private ShoppingItem shoppingItem;

    private float tempQuantity;
    private float tempTotalPrice;


    private ArrayAdapter<String> adapter;

    private static final String[] unitItems = {"none", "each", "kg", "g", "gallon"};


    /**
     * Create a new instance of ItemDialogFragment,
     * providing "position" as an argument
     */
    public static ItemDialogFragment newInstance(int position) {
        ItemDialogFragment f = new ItemDialogFragment();

        // Supply position as an argument
        Bundle args = new Bundle();
        Log.i("newInstance", "position" + position);
        args.putInt("position", position);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialogfragment_item, container, false);


        if (container == null) {
            Log.i("container", "is null");
            return null;
        }


        if(getArguments() == null) {
            Log.i("getArguments", "is null");
            return null;
        }
        position = getArguments().getInt("position");
        shoppingItem = MainActivity.getShoppingItems().get(position);

        tempQuantity = shoppingItem.getQuantity();
        tempTotalPrice = shoppingItem.getItemTotalPrice();

        tvItemName = (TextView) rootView.findViewById(R.id.tvItem_name);
        tvItemName.setText(shoppingItem.getProduct().getName());

        tvUnitPrice = (TextView) rootView.findViewById(R.id.unitPrice);
        tvUnitPrice.setText("Unit Price: " + shoppingItem.getProduct().getUnit_price() + "$");

        tvTotalPrice = (TextView) rootView.findViewById(R.id.totalPrice);
        tvTotalPrice.setText("Total: " +shoppingItem.getItemTotalPrice() + "$");

        tvRatioTPS = (TextView) rootView.findViewById(R.id.tvratioTPS);
        tvRatioTPS.setText("RatioTPS: " + shoppingItem.getProduct().getCategory().getRatioTPS());

        tvRatioTVQ = (TextView) rootView.findViewById(R.id.tvratioTVQ);
        tvRatioTVQ.setText("RatioTVQ: " + shoppingItem.getProduct().getCategory().getRatioTVQ());

        edQuantity = (EditText) rootView.findViewById(R.id.edQuantity);
        edQuantity.setText(String.valueOf(shoppingItem.getQuantity()));
        edQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edQuantity.getText().toString().length() == 0) {
                    Log.i("edQuantity: ", "is empty");
                    return;
                }

                //shoppingItem.setQuantity(Float.parseFloat(edQuantity.getText().toString()));
                //tvTotalPrice.setText("Total: " + shoppingItem.getItemTotalPrice() + "$");

                tempQuantity = Float.parseFloat(edQuantity.getText().toString());
                tvTotalPrice.setText("Total: " + tempQuantity * shoppingItem.getProduct().getUnit_price() + "$");

            }
        });



        btnDecrease = (ImageButton) rootView.findViewById(R.id.btn_decrease);
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tempQuantity > 1) {

                    tempQuantity -= 1.0f;
                    edQuantity.setText(String.valueOf(tempQuantity));
                    tvTotalPrice.setText("Total: " + tempQuantity * shoppingItem.getProduct().getUnit_price() + "$");
                }
            }
        });

        btnIncrease = (ImageButton) rootView.findViewById(R.id.btn_increase);
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tempQuantity += 1.0f;
                edQuantity.setText(String.valueOf(tempQuantity));

                tvTotalPrice.setText("Total: " + tempQuantity * shoppingItem.getProduct().getUnit_price() + "$");
            }
        });



        spinnerUnit = (Spinner) rootView.findViewById(R.id.spinnerUnit);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, unitItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(adapter);

        spinnerUnit.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnDelete = (ImageButton) rootView.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edQuantity.getWindowToken(),0);
                MainActivity.removeOneShoppingItem(position);
                // return to previous fragment
                PaymentFragment.StartCommunication comm = (PaymentFragment.StartCommunication) getActivity();
                getFragmentManager().popBackStackImmediate();

            }
        });

        btnSave = (ImageButton) rootView.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edQuantity.getWindowToken(),0);
                shoppingItem.setQuantity(tempQuantity);
               // return to previous fragment
                getFragmentManager().popBackStackImmediate();
            }
        });

        btnCancel = (ImageButton) rootView.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edQuantity.getWindowToken(),0);
                getFragmentManager().popBackStackImmediate();
            }
        });


        return rootView;
    }
























}
