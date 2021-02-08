//PID parameters
const double kp = PROPORTIONAL_CONSTANT, ki = INTEGRAL_CONSTANT, kd = DIFFERENTIAL_CONSTANT;

//Create three double to store error history
double f_error_1 = 0, f_error_2 = 0, f_error_3 = 0;
double f_control_output = INITIAL_VALUE, f_control_output_change = 0;

//loop
while(true)
{
	f_error_1 = error.Get(); //refresh error here
	f_error_2 = f_error_1;
	f_error_3 = f_error_2;

	//change of the output
	f_control_output_change = \
	f_error_1 * kp +\ //proportional term
	(f_error_1 - f_error_2) * ki +\ //integral term
	((f_error_1 - f_error_2) - (f_error_2 - f_error_3)) * kd; //differential term

	//add the change to control output
	f_control_output += f_control_output_change;

	//constrain control output
	f_control_output = f_control_output > UPPER_BOUND? UPPER_BOUND:f_control_output;
	f_control_output = f_control_output < LOWER_BOUND? LOWER_BOUND:f_control_output;
}
