package controller;


class RegisterFormControllerTest {
    public static void main(String[] args) {
        RegisterFormController ctrl=new RegisterFormController();
        assert ctrl.isName("Kasun Nuwan"); // If our logic is correct this has to be true so that this should move forward
        assert (ctrl.isName("Kasun466")==false); // If our code doesn't work properly assertion exception should be executed
        assert (ctrl.isName("kasun()jfdaj")==false);
        // assert means trusting something if something that we believe is false then an exception will get executed
        // if all the assertions are as same as what we believe, which means if every thing returns true then the code is correct
    }

}