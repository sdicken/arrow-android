package com.arrowfoodcouriers.arrowfood.Fragments;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.json.JSONException;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arrowfoodcouriers.arrowfood.R;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

public class CheckoutFragment extends Fragment
{
	Double price;
	private static final String PAYPAL = "paypal";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.fragment_checkout, container, false);
		
		Bundle bundle = getArguments();
		price = bundle.getDouble(CartFragment.SUBTOTAL);
		
		NumberFormat format = NumberFormat.getCurrencyInstance();
		TextView totalTextView = (TextView) view.findViewById(R.id.checkout_value_text);
		totalTextView.setText(format.format(price));
		
		Button checkoutButton = (Button) view.findViewById(R.id.checkout_button);
		checkoutButton.setOnClickListener(new CheckoutButtonListener());
		
		return view;
	}
	
	private class CheckoutButtonListener implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			PayPalPayment payment = new PayPalPayment(new BigDecimal(price), "USD", "Arrow Food Couriers", PayPalPayment.PAYMENT_INTENT_SALE);
			Intent intent = new Intent(getActivity(), PaymentActivity.class);
			intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
			startActivityForResult(intent, 0);
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		if(resultCode == Activity.RESULT_OK)
    	{
    		PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) 
            {
                try {
                    Log.i(PAYPAL, confirm.toJSONObject().toString(4));

                    // TODO: send 'confirm' to your server for verification.
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.
                    Toast.makeText(getActivity(), "Payment accepted.", Toast.LENGTH_LONG).show();;

                } catch (JSONException e) {
                    Log.e(PAYPAL, "an extremely unlikely failure occurred: ", e);
                }
            }
    	}
	}
}
