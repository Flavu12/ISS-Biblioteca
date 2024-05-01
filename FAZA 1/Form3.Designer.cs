namespace Iss_Lab1
{
    partial class Form3
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            listaExemplare = new ListBox();
            textBox1 = new TextBox();
            Search = new Label();
            button1 = new Button();
            button2 = new Button();
            button3 = new Button();
            SuspendLayout();
            // 
            // listaExemplare
            // 
            listaExemplare.FormattingEnabled = true;
            listaExemplare.ItemHeight = 15;
            listaExemplare.Location = new Point(12, 115);
            listaExemplare.Name = "listaExemplare";
            listaExemplare.Size = new Size(461, 274);
            listaExemplare.TabIndex = 0;
            // 
            // textBox1
            // 
            textBox1.Location = new Point(12, 62);
            textBox1.Name = "textBox1";
            textBox1.Size = new Size(461, 23);
            textBox1.TabIndex = 1;
            // 
            // Search
            // 
            Search.AutoSize = true;
            Search.Location = new Point(12, 44);
            Search.Name = "Search";
            Search.Size = new Size(42, 15);
            Search.TabIndex = 2;
            Search.Text = "Search";
            // 
            // button1
            // 
            button1.Location = new Point(544, 115);
            button1.Name = "button1";
            button1.Size = new Size(208, 92);
            button1.TabIndex = 3;
            button1.Text = "Imprumuta";
            button1.UseVisualStyleBackColor = true;
            // 
            // button2
            // 
            button2.Location = new Point(594, 347);
            button2.Name = "button2";
            button2.Size = new Size(118, 42);
            button2.TabIndex = 4;
            button2.Text = "Logout";
            button2.UseVisualStyleBackColor = true;
            // 
            // button3
            // 
            button3.Location = new Point(544, 232);
            button3.Name = "button3";
            button3.Size = new Size(208, 73);
            button3.TabIndex = 5;
            button3.Text = "Returneaza";
            button3.UseVisualStyleBackColor = true;
            // 
            // Form3
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(button3);
            Controls.Add(button2);
            Controls.Add(button1);
            Controls.Add(Search);
            Controls.Add(textBox1);
            Controls.Add(listaExemplare);
            Name = "Form3";
            Text = "Form3";
            Load += Form3_Load;
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private ListBox listaExemplare;
        private TextBox textBox1;
        private Label Search;
        private Button button1;
        private Button button2;
        private Button button3;
    }
}