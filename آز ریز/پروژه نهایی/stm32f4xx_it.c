/**
  ******************************************************************************
  * @file    stm32f4xx_it.c
  * @brief   Interrupt Service Routines.
  ******************************************************************************
  *
  * COPYRIGHT(c) 2017 STMicroelectronics
  *
  * Redistribution and use in source and binary forms, with or without modification,
  * are permitted provided that the following conditions are met:
  *   1. Redistributions of source code must retain the above copyright notice,
  *      this list of conditions and the following disclaimer.
  *   2. Redistributions in binary form must reproduce the above copyright notice,
  *      this list of conditions and the following disclaimer in the documentation
  *      and/or other materials provided with the distribution.
  *   3. Neither the name of STMicroelectronics nor the names of its contributors
  *      may be used to endorse or promote products derived from this software
  *      without specific prior written permission.
  *
  * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
  * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
  * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
  * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
  * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
  * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
  * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
  * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
  * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  *
  ******************************************************************************
  */
/* Includes ------------------------------------------------------------------*/
#include "stm32f4xx_hal.h"
#include "stm32f4xx.h"
#include "stm32f4xx_it.h"

/* USER CODE BEGIN 0 */

/* USER CODE END 0 */

/* External variables --------------------------------------------------------*/
extern TIM_HandleTypeDef htim4;
extern UART_HandleTypeDef huart5;

/******************************************************************************/
/*            Cortex-M4 Processor Interruption and Exception Handlers         */ 
/******************************************************************************/

/**
* @brief This function handles System service call via SWI instruction.
*/
void SVC_Handler(void)
{
  /* USER CODE BEGIN SVCall_IRQn 0 */

  /* USER CODE END SVCall_IRQn 0 */
  /* USER CODE BEGIN SVCall_IRQn 1 */

  /* USER CODE END SVCall_IRQn 1 */
}

/**
* @brief This function handles Pendable request for system service.
*/
void PendSV_Handler(void)
{
  /* USER CODE BEGIN PendSV_IRQn 0 */

  /* USER CODE END PendSV_IRQn 0 */
  /* USER CODE BEGIN PendSV_IRQn 1 */

  /* USER CODE END PendSV_IRQn 1 */
}

/**
* @brief This function handles System tick timer.
*/
void SysTick_Handler(void)
{
  /* USER CODE BEGIN SysTick_IRQn 0 */

  /* USER CODE END SysTick_IRQn 0 */
  HAL_IncTick();
  HAL_SYSTICK_IRQHandler();
  /* USER CODE BEGIN SysTick_IRQn 1 */

  /* USER CODE END SysTick_IRQn 1 */
}

/******************************************************************************/
/* STM32F4xx Peripheral Interrupt Handlers                                    */
/* Add here the Interrupt Handlers for the used peripherals.                  */
/* For the available peripheral interrupt handler names,                      */
/* please refer to the startup file (startup_stm32f4xx.s).                    */
/******************************************************************************/

int num1 = 0, num2 = 0;
int operation = -1; // -1 for undef, 0 for sum, 1 for div, 2 for mul
int rr;
unsigned char op[1];
unsigned char calc[20];
unsigned char input[1];
int prrrrrrrrrrrr = 0;



int tostring(char str[], int num)
{
    int i, rem, len = 0, n;
 
    n = num;
    while (n != 0)
    {
        len++;
        n /= 10;
    }
    for (i = 0; i < len; i++)
    {
        rem = num % 10;
        num = num / 10;
        str[len - (i + 1)] = rem + '0';
    }
    str[len] = '\0';
		return len;
}

/**
* @brief This function handles EXTI line1 interrupt.
*/
void EXTI1_IRQHandler(void)
{
  /* USER CODE BEGIN EXTI1_IRQn 0 */

  /* USER CODE END EXTI1_IRQn 0 */
  HAL_GPIO_EXTI_IRQHandler(GPIO_PIN_1);
  /* USER CODE BEGIN EXTI1_IRQn 1 */
	
			int row;

		for (int i = 0; i < 4; i++) {

			HAL_GPIO_WritePin(GPIOA, GPIO_PIN_5, (i == 0 ? GPIO_PIN_SET : GPIO_PIN_RESET));
			HAL_GPIO_WritePin(GPIOA, GPIO_PIN_7, (i == 1 ? GPIO_PIN_SET : GPIO_PIN_RESET));
			HAL_GPIO_WritePin(GPIOC, GPIO_PIN_5, (i == 2 ? GPIO_PIN_SET : GPIO_PIN_RESET));
			HAL_GPIO_WritePin(GPIOB, GPIO_PIN_1, (i == 3 ? GPIO_PIN_SET : GPIO_PIN_RESET));

			row = HAL_GPIO_ReadPin(GPIOA, GPIO_PIN_1);
			while(HAL_GPIO_ReadPin(GPIOA, GPIO_PIN_1));
			if (row == 1) {

				switch (i) {
					// 1
					case 0:
						input[0] = '1';
						if(operation == -1) {
							num1 = num1 * 10 + 1;
						} else {
							num2 = num2 * 10 + 1;
						}
					break;
					// 4
					case 1:
						input[0] = '4';
						if(operation == -1) {
							num1 = num1 * 10 + 4;
						} else {
							num2 = num2 * 10 + 4;
						}
						break;
					// 7
					case 2:
						input[0] = '7';
						if(operation == -1) {
							num1 = num1 * 10 + 7;
						} else {
							num2 = num2 * 10 + 7;
						}
						break;
					// .
					case 3:
						// nothing ...
						break;
				} 
					HAL_UART_Transmit(&huart5, input, 1, 100);
				break;
			}
		}
  /* USER CODE END EXTI1_IRQn 1 */
}

/**
* @brief This function handles EXTI line2 interrupt.
*/
void EXTI2_IRQHandler(void)
{
  /* USER CODE BEGIN EXTI2_IRQn 0 */

  /* USER CODE END EXTI2_IRQn 0 */
  HAL_GPIO_EXTI_IRQHandler(GPIO_PIN_2);
  /* USER CODE BEGIN EXTI2_IRQn 1 */
	
			int row;
	

		for (int i = 0; i < 4; i++) {

			HAL_GPIO_WritePin(GPIOA, GPIO_PIN_5, (i == 0 ? GPIO_PIN_SET : GPIO_PIN_RESET));
			HAL_GPIO_WritePin(GPIOA, GPIO_PIN_7, (i == 1 ? GPIO_PIN_SET : GPIO_PIN_RESET));
			HAL_GPIO_WritePin(GPIOC, GPIO_PIN_5, (i == 2 ? GPIO_PIN_SET : GPIO_PIN_RESET));
			HAL_GPIO_WritePin(GPIOB, GPIO_PIN_1, (i == 3 ? GPIO_PIN_SET : GPIO_PIN_RESET));

			row = HAL_GPIO_ReadPin(GPIOA, GPIO_PIN_2);
			while(HAL_GPIO_ReadPin(GPIOA, GPIO_PIN_2));
			
			if (row == 1) {

				switch (i) {
					// 2
					case 0:
						input[0] = '2';
						if(operation == -1) {
							num1 = num1 * 10 + 2;
						} else {
							num2 = num2 * 10 + 2;
						}
					break;
					// 5
					case 1:
						input[0] = '5';
						if(operation == -1) {
							num1 = num1 * 10 + 5;
						} else {
							num2 = num2 * 10 + 5;
						}
						break;
					// 8
					case 2:
						input[0] = '8';
						if(operation == -1) {
							num1 = num1 * 10 + 8;
						} else {
							num2 = num2 * 10 + 8;
						}
						break;
					// 0
					case 3:
						input[0] = '0';
						if(operation == -1) {
							num1 = num1 * 10 + 0;
						} else {
							num2 = num2 * 10 + 0;
						}
						break;
				}
					HAL_UART_Transmit(&huart5, input, 1, 100);
				break;
			}
		}

  /* USER CODE END EXTI2_IRQn 1 */
}

/**
* @brief This function handles EXTI line3 interrupt.
*/
void EXTI3_IRQHandler(void)
{
  /* USER CODE BEGIN EXTI3_IRQn 0 */

  /* USER CODE END EXTI3_IRQn 0 */
  HAL_GPIO_EXTI_IRQHandler(GPIO_PIN_3);
  /* USER CODE BEGIN EXTI3_IRQn 1 */

  /* USER CODE END EXTI3_IRQn 1 */
	
			int row;

		for (int i = 0; i < 4; i++) {

			HAL_GPIO_WritePin(GPIOA, GPIO_PIN_5, (i == 0 ? GPIO_PIN_SET : GPIO_PIN_RESET));
			HAL_GPIO_WritePin(GPIOA, GPIO_PIN_7, (i == 1 ? GPIO_PIN_SET : GPIO_PIN_RESET));
			HAL_GPIO_WritePin(GPIOC, GPIO_PIN_5, (i == 2 ? GPIO_PIN_SET : GPIO_PIN_RESET));
			HAL_GPIO_WritePin(GPIOB, GPIO_PIN_1, (i == 3 ? GPIO_PIN_SET : GPIO_PIN_RESET));

			row = HAL_GPIO_ReadPin(GPIOA, GPIO_PIN_3);
			while(HAL_GPIO_ReadPin(GPIOA, GPIO_PIN_3));
			
			if (row == 1) {

				switch (i) {
					// 3
					case 0:
						input[0] = '3';
						if(operation == -1) {
							num1 = num1 * 10 + 3;
						} else {
							num2 = num2 * 10 + 3;
						}
					break;
					// 6
					case 1:
						input[0] = '6';
						if(operation == -1) {
							num1 = num1 * 10 + 6;
						} else {
							num2 = num2 * 10 + 6;
						}
						break;
					 // 9
					case 2:
						input[0] = '9';
						if(operation == -1) {
							num1 = num1 * 10 + 9;
						} else {
							num2 = num2 * 10 + 9;
						}
						break;
					// =
					case 3:
					input[0] = '=';
					
					if(num1 != 0 && num2 != 0 ){
						HAL_UART_Transmit(&huart5, input, 1, 100);
					}
					if(operation == 0){ rr = num1 + num2; op[0] = '+';}
					else if(operation == 1){ rr = num1 / num2; op[0] = '/';}
					else if(operation == 2){ rr = num1 * num2; op[0] = '*';}
					else {rr = 0; op[0] = '?';}
					char re[200];
					int lll = tostring(re, rr);

					for(int iii = 0; iii < lll; iii ++){
						calc[iii] = re[iii];	
					}
					
					HAL_UART_Transmit(&huart5, calc, lll, 100);
					
					prrrrrrrrrrrr = 1;
					
					num1 = 0;
					num2 = 0;
					operation = -1; // -1 for undef, 0 for sum, 1 for div, 2 for mul
					rr = 0;
					break;
				}
				if(prrrrrrrrrrrr == 0){
					HAL_UART_Transmit(&huart5, input, 1, 100);
				} else {
					prrrrrrrrrrrr = 0;
				}
				break;
			}
		}
}

/**
* @brief This function handles EXTI line4 interrupt.
*/
void EXTI4_IRQHandler(void)
{
  /* USER CODE BEGIN EXTI4_IRQn 0 */

  /* USER CODE END EXTI4_IRQn 0 */
  HAL_GPIO_EXTI_IRQHandler(GPIO_PIN_4);
  /* USER CODE BEGIN EXTI4_IRQn 1 */

  /* USER CODE END EXTI4_IRQn 1 */			
	int row;

		for (int i = 0; i < 4; i++) {

			HAL_GPIO_WritePin(GPIOA, GPIO_PIN_5, (i == 0 ? GPIO_PIN_SET : GPIO_PIN_RESET));
			HAL_GPIO_WritePin(GPIOA, GPIO_PIN_7, (i == 1 ? GPIO_PIN_SET : GPIO_PIN_RESET));
			HAL_GPIO_WritePin(GPIOC, GPIO_PIN_5, (i == 2 ? GPIO_PIN_SET : GPIO_PIN_RESET));
			HAL_GPIO_WritePin(GPIOB, GPIO_PIN_1, (i == 3 ? GPIO_PIN_SET : GPIO_PIN_RESET));

			row = HAL_GPIO_ReadPin(GPIOA, GPIO_PIN_4);
			while(HAL_GPIO_ReadPin(GPIOA, GPIO_PIN_4));

			if (row == 1) {
//				HAL_GPIO_WritePin(GPIOD, GPIO_PIN_12, GPIO_PIN_RESET);
//				HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_RESET);
//				HAL_GPIO_WritePin(GPIOD, GPIO_PIN_14, GPIO_PIN_RESET);
//				HAL_GPIO_WritePin(GPIOD, GPIO_PIN_15, GPIO_PIN_RESET);

				switch (i) {
					// +
					case 0:
						input[0] = '+';
					operation = 0;
					break;
					 // -
					case 1:
						input[0] = '-';
					operation = 3;
						break;
					// *
					case 2:
						input[0] = '*';
						operation = 2;
						break;
					// /
					case 3:
						input[0] = '/';
						operation = 1;
						break;
				}
					HAL_UART_Transmit(&huart5, input, 1, 100);
				break;
			}
		}
}




/**
* @brief This function handles TIM4 global interrupt.
*/
int x = 0;
void TIM4_IRQHandler(void)
{
  /* USER CODE BEGIN TIM4_IRQn 0 */
	x ++;
	
	if(x % 2 == 0) {
		HAL_GPIO_TogglePin(GPIOD,GPIO_PIN_15);
	}
	if(x % 3 == 0) {
		HAL_GPIO_TogglePin(GPIOD,GPIO_PIN_13);
	}
	
	
  /* USER CODE END TIM4_IRQn 0 */
  HAL_TIM_IRQHandler(&htim4);
  /* USER CODE BEGIN TIM4_IRQn 1 */

  /* USER CODE END TIM4_IRQn 1 */
}


unsigned char res[1];

/**
* @brief This function handles UART5 global interrupt.
*/
void UART5_IRQHandler(void)
{
  /* USER CODE BEGIN UART5_IRQn 0 */

  /* USER CODE END UART5_IRQn 0 */
  HAL_UART_IRQHandler(&huart5);
  /* USER CODE BEGIN UART5_IRQn 1 */
extern UART_HandleTypeDef huart5;

	HAL_UART_Receive(&huart5, res, 1, 100);
		if(res[0] == '1') {
			HAL_GPIO_WritePin(GPIOD,GPIO_PIN_12, 1);
		} else if(res[0] == '0') {
			HAL_GPIO_WritePin(GPIOD,GPIO_PIN_14, 1);
		} else {
			HAL_GPIO_WritePin(GPIOD,GPIO_PIN_12, 0);
			HAL_GPIO_WritePin(GPIOD,GPIO_PIN_14, 0);
		}
	
  /* USER CODE END UART5_IRQn 1 */
}

/* USER CODE BEGIN 1 */

/* USER CODE END 1 */
/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
