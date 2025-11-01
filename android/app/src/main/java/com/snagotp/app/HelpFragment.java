package com.snagotp.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;

public class HelpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_help, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialCardView contactSupportCard = view.findViewById(R.id.contact_support_card);
        
        contactSupportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmailClient();
            }
        });
    }

    private void openEmailClient() {
        String supportEmail = getString(R.string.support_email);
        String supportSubject = getString(R.string.support_email_subject);
        
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{supportEmail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, supportSubject);
        
        if (emailIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }
}
