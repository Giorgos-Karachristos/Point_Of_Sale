import matplotlib.pyplot as plt
import sys
import os

def create_bar_chart(x_labels, y_values, x_title, y_title, title, image_name):
    try:
        # Create the Graphs directory if it doesn't exist
        if not os.path.exists('Graphs'):
            os.makedirs('Graphs')

        x = x_labels.split(',')
        # Convert strings of numbers to lists of floats
        y = list(map(float, y_values.split(',')))

        # Create a larger figure
        plt.figure(figsize=(14, 8))

        # Create the bar chart
        plt.bar(x, y, color='skyblue')
        plt.title(title)
        plt.xlabel(x_title)
        plt.ylabel(y_title)

        # Rotate the x-axis labels for better readability
        plt.xticks(rotation=45, ha='right', fontsize=10)

        # Adjust layout to avoid clipping the labels
        plt.tight_layout()

        # Save the bar chart as an image in the Graphs folder
        image_path = os.path.join('Graphs', f'{image_name}.png')
        plt.savefig(image_path)
        
        # Close the plot to free up memory
        plt.close()

    except Exception as e:
        print(f"Error creating the graph: {e}")

if __name__ == '__main__':
    # Expecting x_values, y_values, x_title, y_title, title, and image_name as command-line arguments
    if len(sys.argv) == 7:
        x_labels = sys.argv[1]
        y_values = sys.argv[2]
        x_title = sys.argv[3]
        y_title = sys.argv[4]
        title = sys.argv[5]
        image_name = sys.argv[6]

        create_bar_chart(x_labels, y_values, x_title, y_title, title, image_name)
    else:
        print("Error: Expected 6 arguments (x_labels, y_values, x_title, y_title, title, image_name)")
