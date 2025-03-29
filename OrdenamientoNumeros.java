/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ordenamiento;

/**
 *
 * @author Gerardo GarHer
 */
import javax.swing.*;
import java.util.Arrays;
import java.util.ArrayList;

public class OrdenamientoNumeros {

    public static void main(String[] args) {
        String[] metodos = {
            "1. Bubble Sort (Burbuja)", 
            "2. Selection Sort (Selección)",
            "3. Insertion Sort (Inserción)",
            "4. Merge Sort (Combinación)",
            "5. Quick Sort (Rápido)",
            "6. Heap Sort (Montón)",
            "7. Counting Sort (Conteo)",
            "8. Radix Sort (Raíz)",
            "9. Bucket Sort (Cubo)",
            "0. Salir"
        };

        while (true) {
            // Mostrar menú principal numerado
            String menuMessage = "SELECCIONE UN MÉTODO:\n\n";
            for (String metodo : metodos) {
                menuMessage += metodo + "\n";
            }
            
            String opcionStr = JOptionPane.showInputDialog(
                null,
                menuMessage,
                "Ordenamiento",
                JOptionPane.PLAIN_MESSAGE);
            
            if (opcionStr == null || opcionStr.equals("0")) {
                break;
            }

            try {
                int opcion = Integer.parseInt(opcionStr);
                
                if (opcion < 1 || opcion > 9) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número entre 1 y 9");
                    continue;
                }

                // Pedir cantidad de números
                String cantStr = JOptionPane.showInputDialog("¿Cuántos números desea ordenar?");
                if (cantStr == null) continue;

                int cantidad = Integer.parseInt(cantStr);
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un número mayor a 0");
                    continue;
                }

                // Pedir números uno por uno
                ArrayList<Integer> listaNumeros = new ArrayList<>();
                for (int i = 0; i < cantidad; i++) {
                    String numStr = JOptionPane.showInputDialog("Ingrese el número #" + (i + 1));
                    if (numStr == null) break;
                    listaNumeros.add(Integer.parseInt(numStr));
                }

                if (listaNumeros.size() != cantidad) continue;

                // Convertir a array
                int[] numeros = new int[cantidad];
                for (int i = 0; i < cantidad; i++) {
                    numeros[i] = listaNumeros.get(i);
                }

                int[] copiaOriginal = numeros.clone();
                String metodoUsado = metodos[opcion - 1].substring(3); // Remueve el número

                // Aplicar método seleccionado
                switch (opcion) {
                    case 1 -> bubbleSort(numeros);
                    case 2 -> selectionSort(numeros);
                    case 3 -> insertionSort(numeros);
                    case 4 -> mergeSort(numeros, 0, numeros.length - 1);
                    case 5 -> quickSort(numeros, 0, numeros.length - 1);
                    case 6 -> heapSort(numeros);
                    case 7 -> numeros = countingSort(numeros);
                    case 8 -> radixSort(numeros);
                    case 9 -> numeros = bucketSort(numeros);
                }

                // Mostrar resultados
                String mensaje = "Método usado: " + metodoUsado + "\n\n"
                    + "Array original:\n" + arrayToString(copiaOriginal) + "\n\n"
                    + "Array ordenado:\n" + arrayToString(numeros);

                JOptionPane.showMessageDialog(
                    null,
                    mensaje,
                    "Resultados",
                    JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Error: Ingrese solo números válidos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método auxiliar para convertir array a String
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        return sb.toString();
    }

    // [Aquí irían todos los métodos de ordenamiento igual que en el código anterior]
    // Bubble Sort, Selection Sort, Insertion Sort, etc.
    // ... (Pegar aquí los mismos métodos de ordenamiento del código anterior)
    
    // Bubble Sort
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Selection Sort
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Insertion Sort
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Merge Sort
    private static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Quick Sort
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Heap Sort
    private static void heapSort(int[] arr) {
        int n = arr.length;

        // Construir heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extraer elementos del heap
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    // Counting Sort
    private static int[] countingSort(int[] arr) {
        if (arr.length == 0) return arr;

        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[arr.length];

        for (int num : arr) {
            count[num - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        return output;
    }

    // Radix Sort
    private static void radixSort(int[] arr) {
        if (arr.length == 0) return;

        int max = Arrays.stream(arr).max().getAsInt();

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10];

        for (int num : arr) {
            count[(num / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    // Bucket Sort
    private static int[] bucketSort(int[] arr) {
        if (arr.length == 0) return arr;

        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min;

        int bucketCount = range / arr.length + 1;
        ArrayList<Integer>[] buckets = new ArrayList[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int num : arr) {
            int bucketIndex = (num - min) / arr.length;
            buckets[bucketIndex].add(num);
        }

        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            if (!bucket.isEmpty()) {
                insertionSort(bucket);
                for (int num : bucket) {
                    arr[index++] = num;
                }
            }
        }

        return arr;
    }

    private static void insertionSort(ArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}