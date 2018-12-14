package com.example.robabikya.robabikyaentities.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.greeninnovators.robabikya.models.PickupRequest
import com.greeninnovators.robabikya.models.Product
import com.greeninnovators.robabikya.models.User

class FirebaseUtils {

    companion object {
        val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
        val firebaseDatabase by lazy { FirebaseDatabase.getInstance() }

        fun signUpWithEmailAndPassword(email: String, password: String,
                                                   onSuccess: () -> Unit,
                                                   onFailure: (errorMessage: String) -> Unit) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        onSuccess()
                    } else if (!it.isSuccessful) {
                        onFailure(it.exception!!.localizedMessage)
                    }
                }
        }

        fun createUserInDatabase(user: User,
                                 onSuccess: () -> Unit,
                                 onFailure: (errorMessage: String) -> Unit) {
            firebaseDatabase.reference.child("users")
                .child(user.uid).setValue(user)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        onSuccess()
                    } else if (!it.isSuccessful) {
                        onFailure(it.exception!!.localizedMessage)
                    }
                }
        }

        fun signInWithEmailAndPassword(email: String, password: String,
                                       onSuccess: () -> Unit,
                                       onFailure: (errorMessage: String) -> Unit) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        onSuccess()
                    } else if (!it.isSuccessful) {
                        onFailure(it.exception!!.localizedMessage)
                    }
                }
        }

        fun getUserProfileFromFirebase(onSuccess: (returnedObject: User) -> Unit,
                                                           onFailure: () -> Unit) {
            firebaseDatabase.reference.child("users").child(firebaseAuth.currentUser!!.uid)
                .addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    onFailure()
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    onSuccess(dataSnapshot.getValue(User::class.java) as User)
                }
            })
        }

        fun createProductInDatabase(product: Product,
                                    onSuccess: () -> Unit,
                                    onFailure: () -> Unit) {
            firebaseDatabase.reference.child("users")
                .child(firebaseAuth.currentUser!!.uid)
                .child("bin").child("products").child(product.id).setValue(product)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        onSuccess()
                    } else if (!it.isSuccessful) {
                        onFailure()
                    }
                }
        }


        fun getUserRequests(onSuccess: (Map<String, PickupRequest>) -> Unit, onFailure: () -> Unit) {
            firebaseDatabase.reference.child("users").child(firebaseAuth.currentUser!!.uid).child("requests")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            onFailure()
                        }

                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            onSuccess(dataSnapshot.getValue(HashMap<String, PickupRequest>()::class.java)!!)
                        }
                    })
        }

        fun getUserProducts(onSuccess: (Map<String, Product>) -> Unit, onFailure: () -> Unit){
            firebaseDatabase.reference.child("users").child(firebaseAuth.currentUser!!.uid).child("bin").child("products")
                    .addValueEventListener(object : ValueEventListener{
                        override fun onCancelled(p0: DatabaseError) {
                            onFailure()
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            onSuccess(p0.getValue(HashMap<String, Product>()::class.java)!!)
                        }

                    })
        }







        fun signOut() {
            firebaseAuth.signOut()
        }

    }
}