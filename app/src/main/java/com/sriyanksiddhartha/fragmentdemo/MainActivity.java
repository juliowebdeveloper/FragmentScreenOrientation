package com.sriyanksiddhartha.fragmentdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

	private static final String TAG = MainActivity.class.getSimpleName();

	private FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		manager = getFragmentManager();
        //Sempre que houve qualquer mudança no backstack, esse metodo será chamado que foi implementado no final
        manager.addOnBackStackChangedListener(this);
	}

	public void addFragmentA(View view) {

		FragmentA fragmentA = new FragmentA();

		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.container, fragmentA, "fragA");
        transaction.addToBackStack("AddFragA");
		transaction.commit();
	}

	public void removeFragmentA(View view) {

		FragmentA fragmentA = (FragmentA) manager.findFragmentByTag("fragA");
		FragmentTransaction transaction = manager.beginTransaction();

		if (fragmentA != null) {
			transaction.remove(fragmentA);
            transaction.addToBackStack("RemoveFragA");
            transaction.commit();
		} else {
			Toast.makeText(this, "Fragment A not Found", Toast.LENGTH_SHORT).show();
		}
	}

	public void addFragmentB(View view) {

		FragmentB fragmentB = new FragmentB();

		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.container, fragmentB, "fragB");
        transaction.addToBackStack("AddFragB");//Adicionando toda a transação para o backstack
		transaction.commit();
	}

	public void removeFragmentB(View view) {

		FragmentB fragmentB = (FragmentB) manager.findFragmentByTag("fragB");
		FragmentTransaction transaction = manager.beginTransaction();

		if (fragmentB != null) {
			transaction.remove(fragmentB);
            transaction.addToBackStack("RemoveFragB");
			transaction.commit();
		} else {
			Toast.makeText(this, "Fragment B not Found", Toast.LENGTH_SHORT).show();
		}
	}

	public void replaceByFragmentA(View view) {

		FragmentA fragmentA = new FragmentA();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.container, fragmentA, "fragA");
        transaction.addToBackStack("ReplaceByFragA");
        transaction.commit();
	}

	public void replaceByFragmentB(View view) {

		FragmentB fragmentB = new FragmentB();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.container, fragmentB, "fragB");
        transaction.addToBackStack("ReplaceByFragB");

        transaction.commit();
	}

	public void attachFragmentA(View view) {

		FragmentA fragmentA = (FragmentA) manager.findFragmentByTag("fragA");
		FragmentTransaction transaction = manager.beginTransaction();

		if (fragmentA != null) {
			transaction.attach(fragmentA);
            transaction.addToBackStack("AttachFragA");
			transaction.commit();
		} else {
			Toast.makeText(this, "Fragment A not Found", Toast.LENGTH_SHORT).show();
		}
	}

	public void detachFragmentA(View view) {

		FragmentA fragmentA = (FragmentA) manager.findFragmentByTag("fragA");
		FragmentTransaction transaction = manager.beginTransaction();

		if (fragmentA != null) {
			transaction.detach(fragmentA);
            transaction.addToBackStack("DetachFragA");
			transaction.commit();
		} else {
			Toast.makeText(this, "Fragment A not Found", Toast.LENGTH_SHORT).show();
		}
	}

	public void showFragmentA(View view) {

		FragmentA fragmentA = (FragmentA) manager.findFragmentByTag("fragA");
		FragmentTransaction transaction = manager.beginTransaction();

		if (fragmentA != null) {
			transaction.show(fragmentA);
            transaction.addToBackStack("ShowFragA");
			transaction.commit();
		} else {
			Toast.makeText(this, "Fragment A not Found", Toast.LENGTH_SHORT).show();
		}
	}

	public void hideFragmentA(View view) {

		FragmentA fragmentA = (FragmentA) manager.findFragmentByTag("fragA");
		FragmentTransaction transaction = manager.beginTransaction();

		if (fragmentA != null) {
			transaction.hide(fragmentA);
            transaction.addToBackStack("HideFragA");
			transaction.commit();
		} else {
			Toast.makeText(this, "Fragment A not Found", Toast.LENGTH_SHORT).show();
		}
	}

    //Simplesmente ao apertar o back, o ultimo será removido.
	public void dummyBackButtonClick(View view) {
		manager.popBackStack();
	}
//Removendo da pilha do backstack a partir de uma tag e passando a Flag Inclusive
    //Inclusive - remove todas as transactions antes de achar a transaction  passada em parametro incluindo-a
    //Utilziando Tags é possivel pular direto para o ponto da pilha da transação desejada
	public void pop_addFragA_inclusive_transaction(View view) {
		manager.popBackStack("AddFragA", FragmentManager.POP_BACK_STACK_INCLUSIVE);

	}

	public void pop_addFragB_transaction(View view) {
		manager.popBackStack("AddFragB", 0); //Sem a Flag, passando zero ele remove todas as anteriores mas nao remove essa
	}

	//Quando ouver qualquer mudança no Backstack esse metodo será chamado:
	@Override
	public void onBackStackChanged() {
			int length= manager.getBackStackEntryCount();

		StringBuilder msg = new StringBuilder();
		for(int i = length -1; i >=0; i--){
			msg.append("index ").append(i).append(" : ");
			msg.append(manager.getBackStackEntryAt(i).getName());
			msg.append("\n");

			Log.i(TAG, 	"\n" + msg.toString() + "\n");
		}
	}



    //Para o botão back
    @Override
    public void onBackPressed() {
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
